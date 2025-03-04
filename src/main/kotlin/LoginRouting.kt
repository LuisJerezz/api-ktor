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
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest, "Formato JSON inválido")
            } catch (e: Exception) {
                call.respond(
                    HttpStatusCode.InternalServerError,
                    "Error interno: ${e.message ?: "Desconocido"}"
                )
            }
        }
    }
}

fun Route.protectedRoutes() {
    authenticate("auth-jwt") {
        get("/me") {
            val principal = call.principal<JWTPrincipal>()
            val dni = principal?.payload?.getClaim("dni")?.asString()

            val user = dni?.let { ProviderUseCase.getUserByDni(it) }

            if (user != null) {
                call.respond(HttpStatusCode.OK, user)
            } else {
                call.respond(HttpStatusCode.NotFound, "Usuario no encontrado")
            }
        }
    }
}