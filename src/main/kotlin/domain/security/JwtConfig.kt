package com.example.domain.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.jwt.*
import kotlinx.coroutines.runBlocking
import java.util.*

object JwtConfig {
    private const val secret = "super_secret_key"
    private const val issuer = "domain.com"
    private const val audience = "ktor_audience"
    private const val realm = "ktor_realm"
    private val algorithm = Algorithm.HMAC256(secret)

    // Nuevo método: genera un token e incluye un tokenId único
    fun generateTokenWithId(dni: String): Pair<String, String> {
        val tokenId = UUID.randomUUID().toString()
        val token = JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withSubject("Authentication")
            .withClaim("dni", dni)
            .withClaim("tokenId", tokenId)
            .withIssuedAt(Date())
            .sign(algorithm)
        return tokenId to token
    }

    fun configureAuthentication(config: JWTAuthenticationProvider.Config) {
        config.realm = realm
        config.verifier(
            JWT.require(algorithm)
                .withIssuer(issuer)
                .withAudience(audience)
                .build()
        )
        config.validate { credential ->
            try {
                val dni = credential.payload.getClaim("dni").asString()
                val tokenId = credential.payload.getClaim("tokenId").asString()

                println("[DEBUG] Validando token para DNI: $dni")
                println("[DEBUG] TokenId recibido: $tokenId")

                if (dni.isNullOrBlank() || tokenId.isNullOrBlank()) {
                    println("[ERROR] DNI o tokenId vacíos")
                    null
                } else {
                    // Se obtiene el usuario y se compara el tokenId almacenado en la BD
                    val user = runBlocking { com.example.domain.usecases.ProviderUseCase.getUserByDni(dni) }
                    if (user?.token == tokenId) {
                        println("[DEBUG] TokenId en BD: ${user?.token}")
                        JWTPrincipal(credential.payload)
                    } else {
                        println("[DEBUG] TokenId inválido")
                        null
                    }
                }
            } catch (e: Exception) {
                println("[ERROR] Fallo en validación: ${e.message}")
                null
            }
        }
    }
}
