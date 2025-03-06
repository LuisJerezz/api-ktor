package com.example.domain.repository

import com.example.domain.models.LoginResponse
import com.example.domain.models.User

interface UserInterface {
    suspend fun getAllUsers() : List<User>

    suspend fun getUserByDni(dni : String) : User?

    suspend fun updateUserToken(dni:String, token:String) : Boolean
    suspend fun updateUser(user: User, dni: String) : Boolean

    suspend fun getUserByEmail(email : String) : User?

    suspend fun getUsersByName(name : String) : List<User>

    suspend fun deleteUser(dni : String) : Boolean

    suspend fun addUser(user: User) : Boolean


    suspend fun login(email: String, pass: String) : User?

    suspend fun register(user: User) : User?

}