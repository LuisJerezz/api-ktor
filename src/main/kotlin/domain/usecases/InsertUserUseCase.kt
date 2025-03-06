package com.example.domain.usecases

import com.example.domain.models.User
import com.example.domain.repository.UserInterface
import com.example.domain.security.PasswordHashInterface

class InsertUserUseCase(
    private val repository: UserInterface,
    private val passwordHash: PasswordHashInterface
) {
    suspend operator fun invoke(user: User): Boolean {
        return try {
            val hashedUser = user.copy(
                password = passwordHash.hash(user.password ?: ""))
            repository.addUser(hashedUser)
        } catch (e: Exception) {
            false
        }
    }
}

