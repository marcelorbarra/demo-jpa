package com.example.service.impl

import com.example.command.GenreSaveCommand
import com.example.exception.BookNotFoundException
import com.example.exception.GenreNotFoundException
import com.example.model.Book
import com.example.model.Genre
import com.example.model.entity.BookEntity
import com.example.model.entity.GenreEntity
import com.example.repository.GenreRepository
import com.example.service.GenreService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class GenreServiceImpl(val repository: GenreRepository): GenreService {

    val log: Logger = LoggerFactory.getLogger(GenreServiceImpl::class.java)

    override fun findById(id: Long) : Genre {
        log.info("Finding genre by id: $id")
        val genreEntity = repository.findById(id).orElseThrow {
            GenreNotFoundException(id = id)
        }

        return Genre(genreEntity.id,
            genreEntity.name
        )
    }

    override fun create(genreCommand: GenreSaveCommand): Genre {
        val genreEntity = GenreEntity(null, genreCommand.name, emptyList<BookEntity>())

        log.info("Creating genre: ${genreEntity.toString()}")
        val genreCreated = repository.save(genreEntity)

        return Genre(
            genreCreated.id,
            genreCreated.name
        )
    }

    override fun update(id: Long, genreCommand: GenreSaveCommand): Genre {
        val genreEntity = repository.findById(id).orElseThrow {
            GenreNotFoundException(id = id ?: -1)
        }

        genreEntity.name = genreCommand.name

        log.info("Updating genre: ${genreCommand.toString()}")
        val genreEntityUpdated = repository.update(genreEntity)

        return Genre(
            genreEntityUpdated.id,
            genreEntityUpdated.name
        )
    }

    override fun delete(id: Long) {
        val genreEntity = repository.findById(id).orElseThrow {
            GenreNotFoundException(id = id ?: -1)
        }

        log.info("Deleting genre by id: $id")
        repository.deleteById(id)
    }
}