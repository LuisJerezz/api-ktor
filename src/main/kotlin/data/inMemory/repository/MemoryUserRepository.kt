package com.example.data.inMemory.repository

import com.example.data.inMemory.models.UserData
import com.example.domain.models.User
import com.example.domain.repository.UserInterface

class MemoryUserRepository : UserInterface {
    override suspend fun getAllUsers(): List<User> {
        return UserData.listUser
    }

    override suspend fun getUserByDni(dni: String): User? {
        return UserData.listUser.filter { it.dni == dni }.firstOrNull()
    }

    //override suspend fun getUserById(id: Int): User? {
    //    return UserData.listUser.filter { it.id == id }.firstOrNull()
    //}

    override suspend fun getUserByEmail(email: String): User? {
        return UserData.listUser.filter { it.email == email }.firstOrNull()
    }

    override suspend fun getUsersByName(name: String): List<User> {
        return UserData.listUser.filter { it.name == name }
    }

    override suspend fun deleteUser(email: String): Boolean {
        val index = UserData.listUser.indexOfFirst { it.email == email }
        return if (index != -1){
            UserData.listUser.removeAt(index)
            true
        }else{
            false
        }
    }

    override suspend fun addUser(user: User): Boolean {
        val user = user.dni?.let { getUserByDni(it) }
        return if (user!=null){
            false
        }else{
            UserData.listUser.add(user!!)
            true
        }

    }

    override suspend fun login(email: String, pass: String) : User?{
        TODO("Not yet implemented")
    }

    override suspend fun register(user: User): User? {
        TODO("Not yet implemented")
    }

}