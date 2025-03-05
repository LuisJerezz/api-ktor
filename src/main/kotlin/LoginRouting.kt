package com.example

import com.example.domain.models.LoginRequest
import com.example.domain.models.LoginResponse
import com.example.domain.usecases.ProviderUseCase
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.loginRouting() {
    route("/auth") {
        post("/login") {
            try {
                val request = call.receive<LoginRequest>()
                val result = ProviderUseCase.loginUser(request.email, request.password)

                when {
                    result == null -> call.respond(HttpStatusCode.Unauthorized, "Credenciales inválidas")
                    else -> {
                        val (user, token) = result
                        call.respond(HttpStatusCode.OK, LoginResponse(token, user))
                    }
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, mapOf("error" to "Error interno: ${e.message}"))
            }
        }
    }
}
