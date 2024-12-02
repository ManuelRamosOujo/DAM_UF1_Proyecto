package com.example.uf1_proyecto.model

data class SearchResponse(
    val numFound: Int,              // Número total de libros encontrados
    val start: Int,                 // Índice inicial de los resultados
    val docs: List<Book>            // Lista de libros encontrados
)