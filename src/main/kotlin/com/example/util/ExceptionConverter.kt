package com.example.util

import com.example.exception.BookNotFoundException
import com.example.exception.GenreNotFoundException
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import javax.inject.Singleton

@Singleton
class ExceptionConverter {

    fun convert(e: Exception): HttpStatusException = when(e) {
        is GenreNotFoundException -> HttpStatusException(HttpStatus.NOT_FOUND, e.message)
        is BookNotFoundException -> HttpStatusException(HttpStatus.NOT_FOUND, e.message)
        else -> HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
    }

}