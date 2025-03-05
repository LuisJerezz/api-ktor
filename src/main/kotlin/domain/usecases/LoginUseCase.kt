package com.example.domain.usecases

import com.example.domain.models.User
import com.example.domain.repository.UserInterface
import com.example.domain.security.JwtConfig
import com.example.domain.security.PasswordHashInterface

import java.util.UUID

class LoginUseCase(
    private val repository: UserInterface,
    private val passwordHash: PasswordHashInterface
) {
    suspend operator fun invoke(email: String, password: String): Pair<User, String>? {
        val user = repository.getUserByEmail(email) ?: return null

        // Verifica si el hash es BCrypt
        if (!user.password!!.startsWith("\$2a\$")) {
            return null
        }

        return if (passwordHash.verify(password, user.password)) {
            // Generar un nuevo tokenId
            val newTokenId = UUID.randomUUID().toString()

            // Actualizar el usuario con el nuevo tokenId en la base de datos
            repository.updateTokenId(user.dni!!, newTokenId)

            // Generar el token incluyendo el tokenId
            val token = JwtConfig.generateToken(user.dni, newTokenId)
            user.copy(tokenId = newTokenId) to token
        } else {
            null
        }
    }
}
