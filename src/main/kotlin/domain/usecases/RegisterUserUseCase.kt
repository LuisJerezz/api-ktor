package com.example.domain.usecases

import com.example.domain.models.User
import com.example.domain.repository.UserInterface
import com.example.domain.security.PasswordHashInterface
import org.slf4j.LoggerFactory

class RegisterUserUseCase(
    private val repository: UserInterface,
    private val validator: (User) -> Boolean,
    private val passwordHash: PasswordHashInterface
) {
    private val logger = LoggerFactory.getLogger(RegisterUserUseCase::class.java)

    suspend operator fun invoke(user: User): Pair<Boolean, String> {
        return try {
            logger.info("Intentando registrar usuario: ${user.email}")

            // Validación básica de campos
            if (!validator(user)) {
                logger.warn("Validación fallida para usuario: ${user.email}")
                return Pair(false, "Datos de registro incompletos o inválidos")
            }

            // Validación de formato de email
            if (!isValidEmail(user.email ?: "")) {
                logger.warn("Email inválido: ${user.email}")
                return Pair(false, "Formato de email inválido")
            }

            // Verificar si el usuario ya existe por DNI o Email
            val existingByDni = repository.getUserByDni(user.dni ?: "")
            val existingByEmail = repository.getUserByEmail(user.email ?: "")

            when {
                existingByDni != null -> {
                    logger.warn("DNI ya registrado: ${user.dni}")
                    Pair(false, "El DNI ya está registrado")
                }
                existingByEmail != null -> {
                    logger.warn("Email ya registrado: ${user.email}")
                    Pair(false, "El email ya está registrado")
                }
                else -> {
                    // Hash de contraseña seguro
                    val hashedPassword = user.password?.let { passwordHash.hash(it) }
                        ?: return Pair(false, "Error en el proceso de registro")

                    // Validación del hash generado
                    if (!isValidPasswordHash(hashedPassword)) {
                        logger.error("Error generando hash de contraseña para: ${user.email}")
                        return Pair(false, "Error en el proceso de registro")
                    }

                    val userWithHash = user.copy(password = hashedPassword)

                    val success = repository.addUser(userWithHash)

                    if (success) {
                        logger.info("Usuario registrado exitosamente: ${user.email}")
                        Pair(true, "Registro exitoso")
                    } else {
                        logger.error("Error desconocido al registrar: ${user.email}")
                        Pair(false, "Error en el proceso de registro")
                    }
                }
            }
        } catch (e: Exception) {
            logger.error("Error durante el registro de ${user.email}: ${e.message}", e)
            Pair(false, "Error interno del servidor")
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)\$"
        return email.matches(emailRegex.toRegex())
    }

    private fun isValidPasswordHash(hash: String): Boolean {
        return hash.startsWith("\$2a\$") || hash.startsWith("\$2b\$")
    }
}