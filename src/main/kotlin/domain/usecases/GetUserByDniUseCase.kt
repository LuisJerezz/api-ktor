package com.example.domain.usecases

import com.example.domain.models.User
import com.example.domain.repository.UserInterface

class GetUserByDniUseCase (val repository: UserInterface) {
    var dni : String? = null

    suspend operator fun invoke() : User?{
        return if (dni?.isNullOrBlank()==true){
            null
        }else{
            repository.getUserByDni(dni!!)
        }
    }
}