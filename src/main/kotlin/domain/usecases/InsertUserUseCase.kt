package com.example.domain.usecases

import com.example.domain.models.User
import com.example.domain.repository.UserInterface

class InsertUserUseCase (val repository: UserInterface){
    var user : User? = null

    suspend operator fun invoke() : Boolean {
        return if (user == null){
            false
        }else{
            repository.addUser(user!!)
        }
    }

}
