package com.example.controller

import com.example.command.BookSaveCommand
import com.example.exception.BookNotFoundException
import com.example.model.Book
import com.example.service.BookService
import com.example.service.impl.BookServiceImpl
import com.example.util.ExceptionConverter
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*

@Controller("/books")
class BookController(
    val service: BookService,
    val exceptionConverter: ExceptionConverter
) {

    @Get("/{id}", produces = [MediaType.APPLICATION_JSON])
    fun findById(id: Long) : HttpResponse<Book> {
        try {
            val book = service.findById(id)
            return HttpResponse.ok(book)
        } catch (e: BookNotFoundException) {
            throw exceptionConverter.convert(e)
        }
    }

    @Post("/", produces = [MediaType.APPLICATION_JSON])
    fun create(@Body book: BookSaveCommand) : HttpResponse<Book> {
        try {
            val book = service.create(book)
            return HttpResponse.created(book)
        } catch (e: Exception) {
            throw exceptionConverter.convert(e)
        }
    }

    @Put("/{id}")
    fun update(id: Long, @Body book: BookSaveCommand) : HttpResponse<Book> {
        try {
            service.update(id, book)
            return HttpResponse.noContent()
        } catch (e: Exception) {
            throw exceptionConverter.convert(e)
        }
    }

    @Delete("/{id}")
    fun delete(id: Long) : HttpResponse<Book> {
        try {
            service.delete(id)
            return HttpResponse.noContent()
        } catch (e: Exception) {
            throw exceptionConverter.convert(e)
        }
    }

}