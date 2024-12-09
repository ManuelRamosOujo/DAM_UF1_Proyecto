package com.example.uf1_proyecto

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uf1_proyecto.databinding.FragmentHomeBinding
import com.example.uf1_proyecto.reciclerView.BookAdapter
import com.example.uf1_proyecto.viewmodel.BookViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookViewModel: BookViewModel
    private lateinit var bookAdapter: BookAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        if(!bookViewModel.books.isInitialized){
            bookViewModel.fetchBooks(selectRandomString())
        }

        loadList()
        return view
    }

    private fun selectRandomString(): String{
        val strArray : Array<String> = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L","M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
        return strArray.random();
    }

    private fun loadList(){
        bookViewModel.books.observe(viewLifecycleOwner){ books ->
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            bookAdapter = BookAdapter(books, bookViewModel, this)
            binding.recyclerView.adapter = bookAdapter
        }
    }
}