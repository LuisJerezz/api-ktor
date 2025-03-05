package com.example.data.persistence.repository


import com.example.data.persistence.models.UserDao
import com.example.data.persistence.models.UserTable
import com.example.data.persistence.models.suspendTransaction
import com.example.domain.mapper.toUser
import com.example.domain.models.User
import com.example.domain.repository.UserInterface
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update

class PersistenceUserRepository : UserInterface {
    override suspend fun getAllUsers(): List<User> {
        return suspendTransaction {
            UserDao.all().map { it.toUser() }
        }

    }

    override suspend fun getUserByDni(dni: String): User? {
        return suspendTransaction {
            UserDao.find{
                UserTable.dni eq dni
            }
                .limit(1)
                .map { it.toUser() }.firstOrNull()
        }
    }

    override suspend fun updateUser(user: User, dni: String): Boolean {
        var num = 0
        try {
            suspendTransaction {
                num = UserTable.update({ UserTable.dni eq dni }) { statements ->
                    user.name?.let { statements[name] = it }
                    user.email?.let { statements[email] = it }  // Usa 'it' para asignar el valor recibido
                    user.phone?.let { statements[phone] = it }
                    user.image?.let { statements[image] = it }
                    user.disponible?.let { statements[disponible] = it }
                    // Si quisieras actualizar password, también agrégalo aquí
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return num == 1
    }



    override suspend fun getUserByEmail(email: String): User? {
        return suspendTransaction {
            UserDao.find{
                UserTable.email eq email
            }
                .limit(1)
                .map { it.toUser() }.firstOrNull()

        }
    }

    override suspend fun getUsersByName(name: String): List<User> {

        return suspendTransaction {
            UserDao.find{
                UserTable.name eq name
            }.map { it.toUser() }
        }
    }

    override suspend fun deleteUser(dni: String): Boolean = suspendTransaction{
        val num = UserTable
            .deleteWhere { UserTable.dni eq dni }
        num == 1

    }

    override suspend fun addUser(user: User): Boolean {
        return suspendTransaction {
            // Validar solo campos obligatorios (DNI, name, email, password)
            if (user.dni.isNullOrEmpty() || user.name.isNullOrEmpty() ||
                user.email.isNullOrEmpty() || user.password.isNullOrEmpty()) {
                return@suspendTransaction false
            }

            // Verificar si el usuario ya existe
            val existingUser = UserDao.find { UserTable.dni eq user.dni }.firstOrNull()
            if (existingUser != null) return@suspendTransaction false

            // Insertar (phone puede ser null)
            UserDao.new {
                dni = user.dni!!
                name = user.name!!
                email = user.email!!
                password = user.password!!
                phone = user.phone // 👈 No uses "!!", acepta null
                image = user.image
                disponible = user.disponible ?: true
            }
            true
        }
    }

    override suspend fun updateTokenId(dni: String, tokenId: String) {
        suspendTransaction {
            UserTable.update({ UserTable.dni eq dni }) { statements ->
                statements[UserTable.tokenId] = tokenId
            }
        }
    }

    override suspend fun login(email: String, pass: String) : User? {
        TODO("Not yet implemented")
    }

    override suspend fun register(user: User): User? {
        TODO("Not yet implemented")
    }


}