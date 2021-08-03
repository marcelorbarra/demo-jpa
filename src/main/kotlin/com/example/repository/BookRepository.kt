package com.example.repository

import com.example.model.entity.BookEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface BookRepository : CrudRepository<BookEntity, Long> {
}