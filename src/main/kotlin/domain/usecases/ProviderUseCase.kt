package com.example.domain.usecases

import com.example.data.persistence.repository.PersistenceUserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ProviderUseCase {

    private val repository = PersistenceUserRepository()
    val logger : Logger = LoggerFactory.getLogger("UserUseCaseLogger")

    private val getAllUsersUseCase = GetAllUsersUseCase(repository)
    private val getUserByDniUseCase = GetUserByDniUseCase(repository)

    suspend fun getAllUsers() = getAllUsersUseCase()

    suspend fun getUserByDni() = getUserByDniUseCase()

}