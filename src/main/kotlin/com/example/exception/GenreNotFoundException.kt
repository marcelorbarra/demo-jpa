package com.example.exception

data class GenreNotFoundException(
    val id: Long,
    override val message: String = "Genre not found: id=$id"
) : Exception(message)