package com.example.domain.usecases

import com.example.data.persistence.repository.PersistenceUserRepository
import com.example.domain.models.User
import com.example.domain.security.BCryptPasswordHash
import com.example.domain.security.PasswordHashInterface
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
    private val updateUserUseCase = UpdateUserUseCase(repository)
    private val passwordHash: PasswordHashInterface = BCryptPasswordHash()
    private val registerUserUseCase = RegisterUserUseCase(
        repository = repository,
        validator = { user ->
            !user.dni.isNullOrBlank() &&
                    !user.name.isNullOrBlank() &&
                    !user.email.isNullOrBlank() &&
                    !user.password.isNullOrBlank()
        },
        passwordHash = passwordHash
    )
    private val loginUseCase = LoginUseCase(
        repository = repository,
        passwordHash = passwordHash
    )



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

    suspend fun updateUser(updateUser: User?, dni: String?) : Boolean{
        if (updateUser == null){
            logger.warn("Usuario null")
            return false
        }

        updateUserUseCase.user = updateUser
        updateUserUseCase.dni = dni
        return updateUserUseCase()
    }

    suspend fun registerUser(user: User): Pair<Boolean, String> {
        return registerUserUseCase(user)
    }
    suspend fun loginUser(email: String, password: String) = loginUseCase(email, password)

}