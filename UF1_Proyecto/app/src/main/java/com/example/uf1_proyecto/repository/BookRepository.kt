package com.example.uf1_proyecto.repository

import com.example.uf1_proyecto.model.Book
import com.example.uf1_proyecto.network.RetrofitClient

class BookRepository {
    private val apiService = RetrofitClient.apiService
        suspend fun getBooks(query : String): List<Book> {
            val response = apiService.searchBooks(query)
            return response.docs
        }
}