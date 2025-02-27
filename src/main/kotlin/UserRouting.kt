package com.example

import com.example.domain.mapper.toUpdateUser
import com.example.domain.models.User
import com.example.domain.usecases.ProviderUseCase
import io.ktor.http.*
import io.ktor.server.request.*
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
                val user = ProviderUseCase.getUserByDni(userDni)
                if (user == null){
                    call.respond(HttpStatusCode.NotFound, "Empleado no encontrado")
                }else{
                    val updatedUser = user.toUpdateUser()
                    call.respond(updatedUser)
                }
            }


        }

        get("{userDni}"){
            val userDni = call.parameters["userDni"]
            if (userDni.isNullOrBlank()){
                call.respond(HttpStatusCode.BadRequest, "No has pasado un dni correctamente")
                return@get
            }

            val user = ProviderUseCase.getUserByDni(userDni)
            if (user == null){
                call.respond(HttpStatusCode.NotFound, "Usuario no encontrado")
                return@get
            }
            call.respond(user)
        }


        delete("{userDni}"){
            val dni = call.parameters["userDni"]
            ProviderUseCase.logger.warn("Vamos a borrar el usuario con dni $dni")
            dni?.let {
                val res = ProviderUseCase.deleteUser(dni)
                if (!res) {
                    call.respond(HttpStatusCode.NotFound, "Empleado no encontrado para borrar")
                } else {
                    call.respond(HttpStatusCode.NoContent, "NO")
                }
            }?:run {
                call.respond(HttpStatusCode.NoContent, "Debes identificar el empleado")
            }

            return@delete
            }

        post(){
            try{
                val user = call.receive<User>()
                val res = ProviderUseCase.addUser(user)

                if (!res){
                    call.respond(HttpStatusCode.Conflict, "No se pudo insertar")
                    return@post
                }
                call.respond(HttpStatusCode.Created, "INSERTADO")
            } catch (e : Exception){
                call.respond(HttpStatusCode.BadRequest, "ERROR!!!!!!")
            }
        }

        }
    }
