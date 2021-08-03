package com.example.service.impl

import com.example.command.BookSaveCommand
import com.example.exception.BookNotFoundException
import com.example.exception.GenreNotFoundException
import com.example.model.Book
import com.example.model.Genre
import com.example.model.entity.BookEntity
import com.example.repository.BookRepository
import com.example.repository.GenreRepository
import com.example.service.BookService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class BookServiceImpl(val repository: BookRepository,
                      val genreRepository: GenreRepository) : BookService {

    val log: Logger = LoggerFactory.getLogger(BookServiceImpl::class.java)

    override fun findById(id: Long) : Book {
        log.info("Finding book by id: $id")
        val bookEntity = repository.findById(id).orElseThrow {
            BookNotFoundException(id = id)
        }

        return Book(bookEntity.id,
                    bookEntity.name,
                    bookEntity.isbn,
                    Genre(bookEntity.genre.id, bookEntity.genre.name)
        )
    }

    override fun create(bookCommand: BookSaveCommand): Book {
        val genre = genreRepository.findById(bookCommand.idGenre).orElseThrow {
            GenreNotFoundException(id = bookCommand.idGenre)
        }

        val book = BookEntity(null, bookCommand.name, bookCommand.isbn, genre)

        log.info("Creating book: $bookCommand")
        val bookEntity = repository.save(book)

        return Book(
            bookEntity.id,
            bookEntity.name,
            bookEntity.isbn,
            Genre(bookEntity.genre.id, bookEntity.genre.name)
        )
    }

    override fun update(id: Long, bookCommand: BookSaveCommand): Book {
        val bookEntity = repository.findById(id).orElseThrow {
            BookNotFoundException(id = id ?: -1)
        }

        val genre = genreRepository.findById(bookCommand.idGenre).orElseThrow {
            GenreNotFoundException(id = bookCommand.idGenre)
        }

        bookEntity.name = bookCommand.name
        bookEntity.isbn = bookCommand.isbn
        bookEntity.genre = genre

        log.info("Updating book: ${bookCommand.toString()}")
        val bookEntityUpdated = repository.update(bookEntity)

        return Book(
            bookEntityUpdated.id,
            bookEntityUpdated.name,
            bookEntityUpdated.isbn,
            Genre(bookEntityUpdated.genre.id, bookEntityUpdated.genre.name)
        )
    }

    override fun delete(id: Long) {
        val bookEntity = repository.findById(id).orElseThrow {
            BookNotFoundException(id = id ?: -1)
        }

        log.info("Deleting book by id: $id")
        repository.deleteById(id)
    }

}