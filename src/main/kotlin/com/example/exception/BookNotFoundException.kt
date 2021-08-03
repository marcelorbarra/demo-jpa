package com.example.exception

class BookNotFoundException(
    val id: Long,
    override val message: String = "Book not found: $id"
) : Exception(message)