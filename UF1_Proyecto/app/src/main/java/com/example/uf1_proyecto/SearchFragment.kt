package com.example.uf1_proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uf1_proyecto.databinding.FragmentHomeBinding
import com.example.uf1_proyecto.databinding.FragmentSearchBinding
import com.example.uf1_proyecto.viewmodel.BookViewModel

class SearchFragment : Fragment() {
    var _binding: FragmentSearchBinding? = null
    val binding get() = _binding!!
    private lateinit var bookViewModel: BookViewModel
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        val button = binding.searchButton
        val reciclerView = binding.reciclerView

        button.setOnClickListener{
            bookViewModel.fetchBooks("hola")
            bookViewModel.books.observe(viewLifecycleOwner){ books->
                reciclerView.layoutManager = LinearLayoutManager(context)
                bookAdapter = BookAdapter(books)
                reciclerView.adapter = bookAdapter
            }
        }

        return view
    }
}