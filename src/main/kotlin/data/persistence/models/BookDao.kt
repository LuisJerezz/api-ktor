package com.example.data.persistence.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BookDao (id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<BookDao>(BookTable)
    var isbn by BookTable.isbn
    var name by BookTable.name
    var description by BookTable.description
    var image by BookTable.image
}