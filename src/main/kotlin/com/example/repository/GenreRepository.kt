package com.example.repository

import com.example.model.entity.GenreEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface GenreRepository : CrudRepository<GenreEntity, Long> {
}