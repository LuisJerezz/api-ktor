package com.example

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        registerRouting()
        userRouting()
        loginRouting()
        protectedRoutes()


        get("/") {
            call.respondText("Hello World!")
        }

        get("/prueba") {
            call.respondText("prueba")
        }
        // Static plugin. Try to access `/static/index.html`
        staticResources("/static", "static")
    }
}
