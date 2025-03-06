package com.example.domain.repository

import com.example.domain.models.Book

interface BookInterface {
    suspend fun getAllBooks() : List<Book>

    suspend fun getBookByISBN(isbn : String) : Book?

    suspend fun getBookByName(name : String) : Book?

    suspend fun deleteBook(isbn : String) : Boolean

    suspend fun addBook(book: Book) : Boolean

    suspend fun updateBook(book: Book, isbn: String) : Boolean
}