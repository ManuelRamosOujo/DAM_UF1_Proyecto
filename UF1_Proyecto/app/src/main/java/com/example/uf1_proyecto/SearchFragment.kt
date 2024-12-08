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

        if(bookViewModel.books.isInitialized){
            loadList()
        }

        button.setOnClickListener{
            bookViewModel.fetchBooks(binding.textInput.text.toString())

            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.textInput.windowToken, 0)

            binding.textInput.clearFocus()
            loadList()
        }

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