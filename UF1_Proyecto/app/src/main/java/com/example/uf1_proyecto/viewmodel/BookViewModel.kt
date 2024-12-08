package com.example.uf1_proyecto.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto.database.BookDatabase
import com.example.uf1_proyecto.model.Book
import com.example.uf1_proyecto.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val bookRepository = BookRepository()
    private val _books = MutableLiveData<List<Book>>()
    val books : LiveData<List<Book>> get() = _books
    private val _favoriteBooks = MutableLiveData<List<Book>>()
    val favoriteBooks: LiveData<List<Book>> get() = _favoriteBooks
    private val db = BookDatabase.getDatabase(getApplication())

    fun fetchBooks(query : String) {
        viewModelScope.launch {
            try {
                val bookList = bookRepository.getBooks(query)
                _books.postValue(bookList)
            } catch (e: Exception) {
                println("Error" + e)
            }
        }
    }

    fun insertBook(book: Book) {
        viewModelScope.launch {
            if(db.bookDao().getKey(book.key).isNullOrEmpty()){
                db.bookDao().insertBook(book)
            }
        }
    }

    fun getAllBooks() {
        viewModelScope.launch {
            val books = db.bookDao().getAllBooks()
            _favoriteBooks.value = books
        }
    }

    fun getAllKeys(): LiveData<List<String>> {
        val keysLiveData = MutableLiveData<List<String>>()
        viewModelScope.launch {
            val keys = db.bookDao().getAllKeys()
            keysLiveData.postValue(keys)
        }
        return keysLiveData
    }

    fun getKey(value : String): LiveData<String> {
        val keyLiveData = MutableLiveData<String>()
        viewModelScope.launch {
            val key = db.bookDao().getKey(value)
            keyLiveData.postValue(key)
        }
        return keyLiveData
    }
}