package com.example.domain.usecases

import com.example.data.persistence.repository.PersistenceUserRepository
import com.example.domain.models.User

class GetUserByDniUseCase (val repository: PersistenceUserRepository) {
    var dni : String? = null

    suspend operator fun invoke() : User?{
        return if (dni?.isNullOrBlank()==true){
            null
        }else{
            repository.getUserByDni(dni!!)
        }
    }
}