package com.example.data.inMemory.repository

import com.example.data.inMemory.models.UserData
import com.example.domain.models.User
import com.example.domain.repository.UserInterface

class MemoryUserRepository : UserInterface {
    override suspend fun getAllUsers(): List<User> {
        return UserData.listUser
    }

    override suspend fun getUsersByEmail(email: String): List<User> {
        return UserData.listUser.filter { it.email == email }
    }

    override suspend fun getUsersByName(name: String): List<User> {
        return UserData.listUser.filter { it.name == name }
    }

    override suspend fun deleteUser(email: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, pass: String) {
        TODO("Not yet implemented")
    }

    override suspend fun register(user: User): User? {
        TODO("Not yet implemented")
    }

}