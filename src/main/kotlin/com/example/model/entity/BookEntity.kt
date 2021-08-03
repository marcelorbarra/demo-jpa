package com.example.model.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table
data class BookEntity(@Id
                @GeneratedValue(strategy = GenerationType.AUTO)
                @Column(name = "id")
                var id: Long?,
                      @NotNull
                @Column(name = "name", nullable = false)
                var name: String,
                      @NotNull
                @Column(name = "isbn", nullable = false)
                var isbn: String,
                      @NotNull
                @ManyToOne
                @JoinColumn(name = "id_genre")
                var genre: GenreEntity
)