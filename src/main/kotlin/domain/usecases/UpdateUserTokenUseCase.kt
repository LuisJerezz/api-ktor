package com.example.domain.usecases

import com.example.domain.repository.UserInterface

class UpdateUserTokenUseCase(private val repository: UserInterface) {
    suspend operator fun invoke(dni: String, token: String): Boolean {
        return repository.updateUserToken(dni, token)
    }
}