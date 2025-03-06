package com.example.utils

import com.example.AppConfig
import io.ktor.server.config.*
import java.io.File
import java.util.*

object ImageHandler {
    fun saveBase64Image(base64String: String, isbn: String): String? {
        return try {
            val (header, data) = base64String.split(",", limit = 2)
            val format = when {
                header.contains("jpeg") -> "jpg"
                header.contains("png") -> "png"
                else -> throw IllegalArgumentException("Formato de imagen no soportado")
            }

            val bytes = Base64.getDecoder().decode(data.trim())
            val filename = "book_${isbn}_${UUID.randomUUID()}.$format"
            val outputFile = File(AppConfig.imageDir, filename)

            outputFile.writeBytes(bytes)
            filename
        } catch (e: Exception) {
            AppConfig.environment.log.error("Error al guardar imagen: ${e.message}")
            null
        }
    }

    fun deleteImage(filename: String) {
        File(AppConfig.imageDir, filename).takeIf { it.exists() }?.delete()
    }
}