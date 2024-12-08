package com.example.uf1_proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.uf1_proyecto.databinding.FragmentFavoriteBinding
import com.example.uf1_proyecto.databinding.FragmentSearchBinding
import com.example.uf1_proyecto.viewmodel.BookViewModel

class FavoriteFragment : Fragment() {
    var _binding: FragmentFavoriteBinding? = null
    val binding get() = _binding!!
    private lateinit var bookViewModel: BookViewModel
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root
        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]



        return view
    }


}