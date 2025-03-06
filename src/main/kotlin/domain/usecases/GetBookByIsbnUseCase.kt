package com.example.domain.usecases

import com.example.domain.models.Book
import com.example.domain.models.User
import com.example.domain.repository.BookInterface

class GetBookByIsbnUseCase (val repository : BookInterface){
    var isbn : String?= null

    suspend operator fun invoke() : Book? {
        return if (isbn?.isNullOrBlank()==true){
            null
        }else{
            repository.getBookByISBN(isbn!!)
        }
    }
}