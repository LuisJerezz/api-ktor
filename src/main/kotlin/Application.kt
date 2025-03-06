package com.example

import com.example.domain.security.JwtConfig
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*

// Configuración compartida accesible desde cualquier lugar
object AppConfig {
    lateinit var environment: ApplicationEnvironment
    private val imagePath by lazy {
        environment.config.property("ktor.path.images").getString()
    }

    val imageDir: File by lazy {
        File(imagePath).apply {
            if (!exists() && !mkdirs()) {
                environment.log.error("No se pudo crear el directorio de imágenes: $imagePath")
            }
        }
    }
}

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    // Inicializar configuración compartida
    AppConfig.environment = environment

    // Configuración de autenticación JWT
    install(Authentication) {
        jwt("auth-jwt") {
            JwtConfig.configureAuthentication(this)
        }
    }

    // Configuración de serialización JSON
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }

    // Configurar base de datos (mantén tu implementación existente)
    configureDatabase()

    // Configurar rutas principales
    routing {

        configureRouting()
        loginRouting()
        registerRouting()
        bookRouting()
        userRouting()

        // Endpoint para servir imágenes
        get("/images/{imageName}") {
            val imageName = call.parameters["imageName"] ?: run {
                call.respond(HttpStatusCode.BadRequest, "Nombre de imagen no proporcionado")
                return@get
            }

            val imageFile = File(AppConfig.imageDir, imageName)
            if (imageFile.exists() && imageFile.isFile) {
                call.respondFile(imageFile)
            } else {
                call.respond(HttpStatusCode.NotFound, "Imagen no encontrada")
            }
        }
    }
}