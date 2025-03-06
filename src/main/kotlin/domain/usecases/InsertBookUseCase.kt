package com.example.domain.usecases

import com.example.domain.models.Book
import com.example.domain.repository.BookInterface

class InsertBookUseCase(private val repository : BookInterface){
    suspend operator fun invoke(book: Book): Boolean{
        return try {
            repository.addBook(book)
        }catch (e : Exception){
            false
        }
    }
}