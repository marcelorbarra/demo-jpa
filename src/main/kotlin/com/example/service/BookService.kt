package com.example.service

import com.example.command.BookSaveCommand
import com.example.model.Book

interface BookService {

    fun findById(id: Long) : Book

    fun create(c: BookSaveCommand): Book

    fun update(id: Long, c: BookSaveCommand): Book

    fun delete(id: Long)

}