package com.example.uf1_proyecto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uf1_proyecto.model.Book
import com.squareup.picasso.Picasso

class BookAdapter(private val books: List<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        holder.titleTextView.text = book.title
        holder.authorTextView.text = book.author_name?.joinToString(", ")
        holder.yearTextView.text = book.first_publish_year?.toString() ?: "N/A"

        book.cover_i?.let {
            val imageUrl = "https://covers.openlibrary.org/b/id/$it-L.jpg"
            Picasso.get().load(imageUrl).into(holder.coverImageView)
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.book_title)
        val authorTextView: TextView = itemView.findViewById(R.id.book_author)
        val yearTextView: TextView = itemView.findViewById(R.id.book_year)
        val coverImageView: ImageView = itemView.findViewById(R.id.book_cover)
    }
}
