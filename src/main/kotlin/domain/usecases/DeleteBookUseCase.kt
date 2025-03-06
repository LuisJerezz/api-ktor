package com.example.domain.usecases

import com.example.domain.repository.BookInterface

class DeleteBookUseCase (val repository : BookInterface){
    var isbn : String? = null

    suspend operator fun invoke() : Boolean{
        return if(isbn == null){
            false
        }else{
            return repository.deleteBook(isbn!!)
        }
    }
}