package com.example

import com.example.domain.mapper.toUpdateUser
import com.example.domain.usecases.ProviderUseCase
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting(){


    route("/users"){

        get(){

            val users = ProviderUseCase.getAllUsers()
            call.respond(users)


            val userDni = call.request.queryParameters["dni"]
            ProviderUseCase.logger.warn("El dni tiene el valor $userDni")
            if (userDni != null){
                val user = ProviderUseCase.getUserByDni()
                if (user == null){
                    call.respond(HttpStatusCode.NotFound, "Empleado no encontrado")
                }else{
                    val updatedUser = user.toUpdateUser()
                    call.respond(updatedUser)
                }
            }

            val user = ProviderUseCase.get
        }
    }
}