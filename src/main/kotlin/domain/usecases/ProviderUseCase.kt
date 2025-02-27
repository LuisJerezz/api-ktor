package com.example.domain.usecases

import com.example.data.persistence.repository.PersistenceUserRepository
import com.example.domain.models.User
import io.ktor.websocket.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ProviderUseCase {

    private val repository = PersistenceUserRepository()
    val logger : Logger = LoggerFactory.getLogger("UserUseCaseLogger")

    private val getAllUsersUseCase = GetAllUsersUseCase(repository)
    private val getUserByDniUseCase = GetUserByDniUseCase(repository)
    private val deleteUserUseCase = DeleteUserUseCase(repository)
    private val insertUserUseCase = InsertUserUseCase(repository)


    suspend fun getAllUsers() = getAllUsersUseCase()

    suspend fun getUserByDni(dni: String): User?{
        if (dni.isNullOrBlank()){
            logger.warn("El dni está vacío")
            return null
        }

        getUserByDniUseCase.dni = dni
        val user = getUserByDniUseCase()
        return if (user == null){
            logger.warn("No se ha encontrado el usuario con dni $dni")
            null
        }else{
            user
        }
    }


    suspend fun deleteUser(dni: String) : Boolean{
        deleteUserUseCase.dni = dni
        return deleteUserUseCase()
    }

    suspend fun addUser(user: User?) : Boolean{
        if (user == null){
            logger.warn("No existen datos")
            return false
        }

        insertUserUseCase.user =user
        val res = insertUserUseCase()
        return if (!res){
            logger.warn("no se ha insertado el usuario")
            false
        }else{
            true
        }

    }

}