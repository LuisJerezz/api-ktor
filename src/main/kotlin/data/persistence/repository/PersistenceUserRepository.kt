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
            UserDao.find {
                UserTable.dni eq dni
            }
                .limit(1)
                .map { it.toUser() }
                .firstOrNull()
        }
    }

    // Modificado para usar una consulta update directa
    override suspend fun updateUserToken(dni: String, token: String): Boolean {
        return suspendTransaction {
            val rowsUpdated = UserTable.update({ UserTable.dni eq dni }) {
                it[UserTable.token] = token
            }
            rowsUpdated > 0
        }
    }

    override suspend fun updateUser(user: User, dni: String): Boolean {
        var num = 0
        try {
            suspendTransaction {
                num = UserTable.update({ UserTable.dni eq dni }) { statements ->
                    user.name?.let { statements[UserTable.name] = it }
                    user.email?.let { statements[UserTable.email] = it }
                    user.phone?.let { statements[UserTable.phone] = it }
                    user.image?.let { statements[UserTable.image] = it }
                    user.disponible?.let { statements[UserTable.disponible] = it }
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
            UserDao.find {
                UserTable.email eq email
            }
                .limit(1)
                .map { it.toUser() }
                .firstOrNull()
        }
    }

    override suspend fun getUsersByName(name: String): List<User> {
        return suspendTransaction {
            UserDao.find {
                UserTable.name eq name
            }.map { it.toUser() }
        }
    }

    override suspend fun deleteUser(dni: String): Boolean = suspendTransaction {
        val num = UserTable.deleteWhere { UserTable.dni eq dni }
        num == 1
    }

    override suspend fun addUser(user: User): Boolean {
        return suspendTransaction {
            // Validar solo campos obligatorios (DNI, name, email, password)
            if (user.dni.isNullOrEmpty() || user.name.isNullOrEmpty() ||
                user.email.isNullOrEmpty() || user.password.isNullOrEmpty()
            ) {
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
                phone = user.phone
                image = user.image
                disponible = user.disponible ?: true
            }
            true
        }
    }

    override suspend fun login(email: String, pass: String): User? {
        TODO("Not yet implemented")
    }

    override suspend fun register(user: User): User? {
        TODO("Not yet implemented")
    }
}
