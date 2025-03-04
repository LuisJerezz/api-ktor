package com.example.data.persistence.models

import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable : IntIdTable("User") {
    val dni = varchar("dni", 10)
    val name = varchar("name", 50)
    val password = varchar("password", 255)
    val email = varchar("email", 100).uniqueIndex()
    val phone = integer("phone").nullable()  // Campo opcional
    val image = varchar("image", 255).nullable()
    val disponible = bool("disponible").default(true)
}
