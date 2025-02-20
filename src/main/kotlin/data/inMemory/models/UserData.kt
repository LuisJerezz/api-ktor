package com.example.data.inMemory.models

import com.example.domain.models.User

object UserData {
    val listUser = mutableListOf<User>(
        User(
            name = "Juan Pérez",
            email = "juan.perez@example.com",
            password = "password123",
            phone = 123456789,
            urlImage = "https://example.com/images/juan.jpg",
            disponible = true,
            token = "some-token-123"
        ),
        User(
            name = "Ana García",
            email = "ana.garcia@example.com",
            password = "securePassword456",
            phone = 987654321,
            urlImage = "https://example.com/images/ana.jpg",
            disponible = true,
            token = "some-token-456"
        ),
        User(
            name = "Carlos Rodríguez",
            email = "carlos.rodriguez@example.com",
            password = "myPassword789",
            phone = 112233445,
            urlImage = "https://example.com/images/carlos.jpg",
            disponible = false,
            token = "some-token-789"
        )
    )
}
