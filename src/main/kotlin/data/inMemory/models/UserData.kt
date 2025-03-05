package com.example.data.inMemory.models

import com.example.domain.models.User

object UserData {
    val listUser = mutableListOf<User>(
        User(
            dni = "600123001",
            name = "Juan Pérez",
            email = "juan.perez@example.com",
            password = "password123",
            phone = 123456789,
            image = "https://example.com/images/juan.jpg",
            disponible = true,
            tokenId = "null"
        ),
        User(
            dni = "600123002",
            name = "Ana García",
            email = "ana.garcia@example.com",
            password = "securePassword456",
            phone = 987654321,
            image = "https://example.com/images/ana.jpg",
            disponible = true,
            tokenId = "null"
        ),
        User(
            dni = "600123003",
            name = "Carlos Rodríguez",
            email = "carlos.rodriguez@example.com",
            password = "myPassword789",
            phone = 112233445,
            image = "https://example.com/images/carlos.jpg",
            disponible = false,
            tokenId = "null"
        )
    )
}
