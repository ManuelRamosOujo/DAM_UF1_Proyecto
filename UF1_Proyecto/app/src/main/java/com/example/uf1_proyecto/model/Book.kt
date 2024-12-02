package com.example.uf1_proyecto.model

data class Book(
    val title: String?,             // Título del libro
    val author_name: List<String>?, // Lista de autores
    val first_publish_year: Int?,   // Año de primera publicación
    val isbn: List<String>?,        // Lista de ISBNs
    val key: String?                // Identificador único del libro
)
