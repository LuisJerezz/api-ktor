package com.example

import com.example.domain.security.JwtConfig
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(Authentication) {
        jwt("auth-jwt") {
            JwtConfig.configureAuthentication(this)
        }
    }


    configureSerialization()
    configureRouting()
    configureDatabase()
}
