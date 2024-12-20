package com.example.uf1_proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uf1_proyecto.databinding.FragmentFavoriteBinding
import com.example.uf1_proyecto.reciclerView.BookAdapter
import com.example.uf1_proyecto.viewmodel.BookViewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookViewModel: BookViewModel
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root
        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]
        bookViewModel.getAllBooks()

        loadList()
        return view
    }

    private fun loadList(){
        bookViewModel.books.observe(viewLifecycleOwner){ books ->
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            bookAdapter = BookAdapter(books, bookViewModel, this)
            binding.recyclerView.adapter = bookAdapter
        }
    }
}