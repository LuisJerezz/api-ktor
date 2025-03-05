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

    override suspend fun updateUser(user: User, dni: String): Boolean {
        val i = UserData.listUser.indexOfFirst { it.dni == dni }
        return if (i != -1) {
            val originUser = UserData.listUser[i]
            UserData.listUser[i] = originUser.copy(
                name = user.name ?: originUser.name,
                dni = user.dni ?: originUser.dni,
                password = user.password ?: originUser.password,
                email = user.email ?: originUser.email,
                phone = user.phone ?: originUser.phone,
                image = user.image ?: originUser.image,
                disponible = user.disponible ?: originUser.disponible,
                tokenId = user.tokenId ?: originUser.tokenId  // Se actualiza el tokenId si se proporciona
            )
            true
        } else {
            false
        }
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

    override suspend fun updateTokenId(dni: String, tokenId: String) {
        val index = UserData.listUser.indexOfFirst { it.dni == dni }
        if (index != -1) {
            val originUser = UserData.listUser[index]
            UserData.listUser[index] = originUser.copy(tokenId = tokenId)
        }
    }


    override suspend fun login(email: String, pass: String) : User?{
        TODO("Not yet implemented")
    }

    override suspend fun register(user: User): User? {
        TODO("Not yet implemented")
    }

}