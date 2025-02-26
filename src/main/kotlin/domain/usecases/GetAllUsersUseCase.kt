package com.example.domain.usecases

import com.example.domain.models.User
import com.example.domain.repository.UserInterface

class GetAllUsersUseCase( val repository : UserInterface) {
    suspend operator fun invoke() : List<User>  = repository.getAllUsers()
}