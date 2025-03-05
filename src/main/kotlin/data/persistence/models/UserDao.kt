package com.example.data.persistence.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserDao>(UserTable)
    var dni by UserTable.dni
    var name by UserTable.name
    var password by UserTable.password
    var email by UserTable.email
    var phone by UserTable.phone
    var image by UserTable.image
    var disponible by UserTable.disponible
    var tokenId by UserTable.tokenId
}
