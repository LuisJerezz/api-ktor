package com.example

import com.example.domain.mapper.toUpdateUser
import com.example.domain.models.UpdatedUser
import com.example.domain.models.User
import com.example.domain.models.UserRegister
import com.example.domain.usecases.ProviderUseCase
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting(){
    authenticate("auth-jwt") {


        get("/users") {

            val users = ProviderUseCase.getAllUsers()
            call.respond(users)


            val userDni = call.request.queryParameters["dni"]
            ProviderUseCase.logger.warn("El dni tiene el valor $userDni")
            if (userDni != null) {
                val user = ProviderUseCase.getUserByDni(userDni)
                if (user == null) {
                    call.respond(HttpStatusCode.NotFound, "Empleado no encontrado")
                } else {
                    val updatedUser = user.toUpdateUser()
                    call.respond(updatedUser)
                }
            }


        }

        get("/users/{userDni}") {
            val userDni = call.parameters["userDni"]
            if (userDni.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "No has pasado un dni correctamente")
                return@get
            }

            val user = ProviderUseCase.getUserByDni(userDni)
            if (user == null) {
                call.respond(HttpStatusCode.NotFound, "Usuario no encontrado")
                return@get
            }
            call.respond(user)
        }


        delete("/users/{userDni}") {
            val dni = call.parameters["userDni"]
            ProviderUseCase.logger.warn("Vamos a borrar el usuario con dni $dni")
            dni?.let {
                val res = ProviderUseCase.deleteUser(dni)
                if (!res) {
                    call.respond(HttpStatusCode.NotFound, "Empleado no encontrado para borrar")
                } else {
                    call.respond(HttpStatusCode.NoContent, "NO")
                }
            } ?: run {
                call.respond(HttpStatusCode.NoContent, "Debes identificar el empleado")
            }

            return@delete
        }

        post("/users") {
            try {
                val user = call.receive<User>()
                val res = ProviderUseCase.addUser(user)

                if (!res) {
                    call.respond(HttpStatusCode.Conflict, "No se pudo insertar")
                    return@post
                }
                call.respond(HttpStatusCode.Created, "INSERTADO")
            } catch (e: IllegalStateException) {
                call.respond(HttpStatusCode.BadRequest, "Error en el formato de envío de datos o lectura del cuerpo.")
            } catch (e: JsonConvertException) {
                call.respond(HttpStatusCode.BadRequest, " Problemas en la conversión json")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Error en los datos. Probablemente falten.")
            }
        }

        patch("/users/{userDni}") {
            try {
                val dni = call.parameters["userDni"] ?: run {
                    call.respond(HttpStatusCode.BadRequest, "Debes identificar el empleado")
                    return@patch
                }

                // Recibe solo los campos a actualizar
                val updateRequest = call.receive<UpdatedUser>()

                // Recupera el usuario actual de la base de datos
                val currentUser = ProviderUseCase.getUserByDni(dni)
                if (currentUser == null) {
                    call.respond(HttpStatusCode.NotFound, "Usuario no encontrado")
                    return@patch
                }

                // Mezcla los campos: si el campo en updateRequest es null, conserva el valor actual.
                val updatedUser = currentUser.copy(
                    name = updateRequest.name ?: currentUser.name,
                    email = updateRequest.email ?: currentUser.email,
                    password = updateRequest.password ?: currentUser.password,
                    phone = updateRequest.phone ?: currentUser.phone,
                    image = updateRequest.image ?: currentUser.image,
                    disponible = updateRequest.disponible ?: currentUser.disponible
                )

                // Llama al use case para actualizar el usuario
                val res = ProviderUseCase.updateUser(updatedUser, dni)
                if (!res) {
                    call.respond(HttpStatusCode.Conflict, "El empleado no pudo modificarse. Puede que no exista")
                    return@patch
                }

                call.respond(HttpStatusCode.OK, "Se ha actualizado correctamente con dni = $dni")
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest, "Error en el formato JSON enviado")
            } catch (e: Exception) {
                e.printStackTrace()
                call.respond(HttpStatusCode.InternalServerError, "Error inesperado")
            }
        }



    }

}
