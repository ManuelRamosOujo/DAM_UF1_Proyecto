package com.example.uf1_proyecto.model

import androidx.annotation.NonNull
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.TypeConverters
import com.example.uf1_proyecto.database.Converters

@Entity(tableName = "book")
data class Book(
    val title: String?,             // Título del libro
    @TypeConverters(Converters::class) val author_name: List<String>?, // Lista de autores
    val first_publish_year: Int?,   // Año de primera publicación
    @TypeConverters(Converters::class) val isbn: List<String>?,        // Lista de ISBNs
    @PrimaryKey val key: String,   // Identificador único del libro
    val cover_i: Int?,              // Portada del libro
)

@Dao
interface BookDao {
    @Insert
    suspend fun insertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT * FROM Book")
    suspend fun getAllBooks(): List<Book>

    @Query("SELECT [key] FROM Book")
    suspend fun getAllKeys(): List<String>

    @Query("SELECT [key] FROM Book WHERE [key] = :value")
    suspend fun getKey(value: String): String
}