package com.example.uf1_proyecto.network

import retrofit2.Call
import com.example.uf1_proyecto.model.Book
import com.example.uf1_proyecto.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search.json")
    suspend fun searchBooks(@Query("title") title: String): SearchResponse
}