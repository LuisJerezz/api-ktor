package com.example

import com.example.domain.models.Book
import com.example.domain.models.UpdatedBook
import com.example.domain.usecases.ProviderUseCase
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.response.*


fun Route.bookRouting() {
    authenticate("auth-jwt") {
        get("/books") {
            val books = ProviderUseCase.getAllBooks()
            call.respond(books)
        }

        get("/books/{isbn}") {
            val isbn = call.parameters["isbn"]
            if (isbn.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "No has proporcionado un ISBN válido")
                return@get
            }

            val book = ProviderUseCase.getBookByISBN(isbn)
            if (book == null) {
                call.respond(HttpStatusCode.NotFound, "Libro no encontrado")
                return@get
            }
            call.respond(book)
        }

        delete("/books/{isbn}"){
            val isbn = call.parameters["isbn"]
            ProviderUseCase.logger.warn("Vamos a borrar un libro")
            isbn?.let {
                val res = ProviderUseCase.deleteBook(isbn)
                if (!res){
                    call.respond(HttpStatusCode.NotFound, "Libro no encontrado")
                }else{
                    call.respond(HttpStatusCode.NoContent, "NO SE BORRA NADA")
                }
            } ?: run {
                call.respond(HttpStatusCode.NoContent, "Debes indicar el isbn en la ruta")
            }
            return@delete
        }

        post("/books"){
            try {
                val book = call.receive<Book>()
                val res = ProviderUseCase.addBook(book)

                if (!res){
                    call.respond(HttpStatusCode.Conflict, "No se pudo insertar")
                    return@post
                }
                call.respond(HttpStatusCode.Created, "INSERTADO")
            }catch (e: IllegalStateException) {
                call.respond(HttpStatusCode.BadRequest, "Error en el formato de envío de datos o lectura del cuerpo.")
            } catch (e: JsonConvertException) {
                call.respond(HttpStatusCode.BadRequest, " Problemas en la conversión json")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Error en los datos. Probablemente falten.")
            }
        }

        patch("/books/{isbn}") {
            try {
                val isbn = call.parameters["isbn"] ?: run {
                    call.respond(HttpStatusCode.BadRequest, "Debes proporcionar el ISBN del libro")
                    return@patch
                }

                val updateRequest = call.receive<UpdatedBook>()

                val currentBook = ProviderUseCase.getBookByISBN(isbn)
                if (currentBook == null) {
                    call.respond(HttpStatusCode.NotFound, "Libro no encontrado")
                    return@patch
                }

                val updatedBook = currentBook.copy(
                    name = updateRequest.name ?: currentBook.name,
                    description = updateRequest.description ?: currentBook.description,
                    image = updateRequest.image ?: currentBook.image
                )

                val success = ProviderUseCase.updateBook(updatedBook, isbn)
                if (!success) {
                    call.respond(HttpStatusCode.Conflict, "El libro no pudo actualizarse")
                    return@patch
                }

                call.respond(HttpStatusCode.OK, "Libro actualizado correctamente (ISBN: $isbn)")
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest, "Formato JSON inválido")
            } catch (e: Exception) {
                e.printStackTrace()
                call.respond(HttpStatusCode.InternalServerError, "Error interno del servidor")
            }
        }

    }
}