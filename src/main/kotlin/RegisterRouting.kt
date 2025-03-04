package com.example

import com.example.domain.models.User
import com.example.domain.models.UserRegister
import com.example.domain.usecases.ProviderUseCase
import io.ktor.server.response.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.registerRouting() {
    post("/register") {

        fun UserRegister.toDomainUser() = User(
            dni = this.dni,
            name = this.name,
            email = this.email,
            password = this.password,
            phone = this.phone,
            image = this.image,
            disponible = this.disponible,
        )

        try {
            val userRegistration = call.receive<UserRegister>()
            val user = userRegistration.toDomainUser()

            val (success, message) = ProviderUseCase.registerUser(user)

            if (success) {
                call.respond(HttpStatusCode.Created, mapOf("message" to message))
            } else {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to message))
            }
        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.InternalServerError,
                mapOf("error" to "Error en el registro: ${e.message}")
            )
        }


    }

}