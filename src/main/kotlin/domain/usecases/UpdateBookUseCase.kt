package com.example.domain.usecases

import com.example.domain.models.Book
import com.example.domain.repository.BookInterface

class UpdateBookUseCase(val repository : BookInterface) {
    var book : Book? = null
    var isbn : String? = null

    suspend operator fun invoke() : Boolean{
        return if (book == null || isbn==null){
            false
        }else{
            repository.updateBook(book!!, isbn!!)
        }
    }
}