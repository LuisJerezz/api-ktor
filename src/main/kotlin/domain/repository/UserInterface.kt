package com.example.domain.repository

import com.example.domain.models.User

interface UserInterface {
    suspend fun getAllUsers() : List<User>

    suspend fun getUsersByEmail(email : String) : List<User>

    suspend fun getUsersByName(name : String) : List<User>

    suspend fun deleteUser(email : String) : Boolean

    suspend fun login(email: String, pass: String)

    suspend fun register(user: User) : User?

}