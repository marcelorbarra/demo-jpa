package com.example.model.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table
data class GenreEntity(@Id
                 @GeneratedValue(strategy = GenerationType.AUTO)
                 @Column(name = "id")
                 var id: Long?,
                       @NotNull
                 @Column(name = "name")
                 var name: String,
                       @OneToMany(mappedBy = "genre")
                 var books: List<BookEntity>
)