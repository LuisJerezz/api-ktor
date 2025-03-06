package com.example.data.persistence.repository

import com.example.data.persistence.models.BookDao
import com.example.data.persistence.models.suspendTransaction
import com.example.domain.mapper.toBook
import com.example.domain.models.Book
import com.example.domain.repository.BookInterface

class PersistenceBookRepository : BookInterface{
    override suspend fun getAllBooks(): List<Book> {
        return suspendTransaction {
            BookDao.all().map { it.toBook() }
        }
    }

    override suspend fun getBookByISBN(isbn: String): Book? {
        TODO("Not yet implemented")
    }

    override suspend fun getBookByName(name: String): Book? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBook(isbn: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun addBook(book: Book): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateBook(book: Book, isbn: String): Boolean {
        TODO("Not yet implemented")
    }

}