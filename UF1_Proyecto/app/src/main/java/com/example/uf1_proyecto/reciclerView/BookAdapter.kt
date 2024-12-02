package com.example.uf1_proyecto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.uf1_proyecto.model.Book

class BookAdapter(private val books: List<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    // Crear la vista para cada item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    // Asignar datos a la vista del item
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        // Asignar el título, autor y año de publicación
        holder.titleTextView.text = book.title
        holder.authorTextView.text = book.author_name?.joinToString(", ")
        holder.yearTextView.text = book.first_publish_year?.toString() ?: "N/A"
    }

    // Retorna el tamaño de la lista
    override fun getItemCount(): Int {
        return books.size
    }

    // Vista del item
    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.book_title)
        val authorTextView: TextView = itemView.findViewById(R.id.book_author)
        val yearTextView: TextView = itemView.findViewById(R.id.book_year)
    }
}
