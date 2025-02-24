package com.example.data.persistence.models

import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable : IntIdTable("User") {
    var name = varchar("name", 50)
    val email = varchar("email", 100).uniqueIndex()
    val password = varchar("password", 255)
    val phone = integer("phone")
    val image = varchar("image", 255).nullable()
    val disponible = bool("disponible")
    val token = varchar("token", 255).nullable()
}