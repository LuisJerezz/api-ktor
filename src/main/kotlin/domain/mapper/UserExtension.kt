package com.example.domain.mapper

import com.example.data.persistence.models.UserDao
import com.example.domain.models.User

fun User.toUpdateUser() : User{
    return User(
        dni = dni,
        name = name,
        email = email,
        password = password,
        phone = phone,
        image = image,
        disponible = disponible,
        token = token
    )

}


fun UserDao.toUser() : User{
    val user = User(
        dni ?: "600123001",
        name ?: "Sin nombre",
        email,
        password,
        phone ?: 123456789,
        image ?: "null",
        disponible,
        token
    )
    return user
}

