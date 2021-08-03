package com.example.service

import com.example.command.GenreSaveCommand
import com.example.model.Genre

interface GenreService {

    fun findById(id: Long) : Genre

    fun create(genreCommand: GenreSaveCommand): Genre

    fun update(id: Long, genreCommand: GenreSaveCommand): Genre

    fun delete(id: Long)
}