package com.example.command

data class BookSaveCommand(
    val name: String,
    val isbn: String,
    val idGenre: Long
)