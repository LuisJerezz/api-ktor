package com.example.domain.usecases

import com.example.domain.models.Book
import com.example.domain.repository.BookInterface

class GetBooksUseCase (val repository : BookInterface){
    suspend operator fun invoke() : List<Book> = repository.getAllBooks()
}