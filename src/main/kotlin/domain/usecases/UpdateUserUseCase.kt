package com.example.domain.usecases

import com.example.domain.models.User
import com.example.domain.repository.UserInterface

class UpdateUserUseCase (val repository : UserInterface) {
    var user : User? = null
    var dni : String? = null

    suspend operator fun invoke() : Boolean{
        return if (user == null || dni == null){
            false
        }else{
            repository.updateUser(user!!, dni!!)
        }
    }

}

