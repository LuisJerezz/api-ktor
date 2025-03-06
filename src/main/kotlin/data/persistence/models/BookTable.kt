package com.example.data.persistence.models

import org.jetbrains.exposed.dao.id.IntIdTable

object BookTable : IntIdTable("Book") {
    val isbn = varchar("isbn", 255).uniqueIndex()
    val name = varchar("name", 200)
    val description = varchar("description", 255)
    val image = varchar("image", 255).nullable()
}