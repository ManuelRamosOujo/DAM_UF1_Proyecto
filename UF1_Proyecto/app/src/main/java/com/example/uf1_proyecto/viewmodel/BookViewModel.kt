package com.example.uf1_proyecto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uf1_proyecto.model.Book
import com.example.uf1_proyecto.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    private val bookRepository = BookRepository()
    private val _books = MutableLiveData<List<Book>>()
    val books : LiveData<List<Book>> get() = _books
    var favoriteBooks  : MutableList<Book> = mutableListOf()

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

    fun setFavoriteByKey(key : String) {
        books.value!!.forEach{
            if (it.key == key){
                favoriteBooks.add(it)
            }
        }
    }
}