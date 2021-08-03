package com.example.controller

import com.example.command.BookSaveCommand
import com.example.command.GenreSaveCommand
import com.example.model.Book
import com.example.model.Genre
import com.example.service.GenreService
import com.example.service.impl.GenreServiceImpl
import com.example.util.ExceptionConverter
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*

@Controller("/genres")
class GenreController(
    val service: GenreService,
    val exceptionConverter: ExceptionConverter
) {

    @Get("/{id}", produces = [MediaType.APPLICATION_JSON])
    fun findById(id: Long) : HttpResponse<Genre> {
        try {
            val genre = service.findById(id)
            return HttpResponse.ok(genre)
        } catch (e: Exception) {
            throw exceptionConverter.convert(e)
        }
    }

    @Post("/", produces = [MediaType.APPLICATION_JSON])
    fun create(@Body genreSaveCommand: GenreSaveCommand) : HttpResponse<Genre> {
        try {
            val genre = service.create(genreSaveCommand)
            return HttpResponse.created(genre)
        } catch (e: Exception) {
            throw exceptionConverter.convert(e)
        }
    }

    @Put("/{id}")
    fun update(id: Long, @Body genre: GenreSaveCommand) : HttpResponse<Genre> {
        try {
            val book = service.update(id, genre)
            return HttpResponse.noContent()
        } catch (e: Exception) {
            throw exceptionConverter.convert(e)
        }
    }

    @Delete("/{id}")
    fun delete(id: Long) : HttpResponse<Genre> {
        try {
            val book = service.delete(id)
            return HttpResponse.noContent()
        } catch (e: Exception) {
            throw exceptionConverter.convert(e)
        }
    }
}