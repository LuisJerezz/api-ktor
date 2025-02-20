package com.example.data.persistence.models

import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable : IntIdTable("User") {
    val email = varchar("email", 100).uniqueIndex()
}