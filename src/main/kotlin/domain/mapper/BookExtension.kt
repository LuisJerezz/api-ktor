package com.example.domain.mapper

import com.example.data.persistence.models.BookDao
import com.example.domain.models.Book

fun BookDao.toBook() : Book{
    val book = Book(
        isbn ?: "prueba",
        name ?: "Sin nombre",
        description ?: "---",
        image ?: "image"
    )
    return book
}