package com.example.domain.usecases

import com.example.domain.models.User
import com.example.domain.repository.UserInterface
import com.example.domain.security.JwtConfig
import com.example.domain.security.PasswordHashInterface

class LoginUseCase(
    private val repository: UserInterface,
    private val passwordHash: PasswordHashInterface,
    private val jwtConfig: JwtConfig
) {
    suspend operator fun invoke(email: String, password: String): Pair<User, String>? {
        val user = repository.getUserByEmail(email) ?: return null

        return if (passwordHash.verify(password, user.password!!)) {
            // Generar nuevo token con tokenId único
            val (newTokenId, newToken) = jwtConfig.generateTokenWithId(user.dni!!)
            // Actualizar el token en la base de datos (se almacena el tokenId)
            repository.updateUserToken(user.dni, newTokenId)
            Pair(user.copy(token = newTokenId), newToken)
        } else {
            null
        }
    }
}
