package com.example.uf1_proyecto

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.uf1_proyecto.model.Book
import com.example.uf1_proyecto.viewmodel.BookViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class BookAdapter(private val books: List<Book>, private val viewModel: BookViewModel, private var owner: LifecycleOwner) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
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

        if (book.cover_i == null){
            holder.coverImageView.setBackgroundColor(Color.GRAY)
            holder.imageText.visibility = View.VISIBLE;
            holder.coverImageView.setImageDrawable(null)
        } else {
            book.cover_i.let {
                val imageUrlL = "https://covers.openlibrary.org/b/id/$it-L.jpg"
                val imageUrlM = "https://covers.openlibrary.org/b/id/$it-M.jpg"
                val imageUrlS = "https://covers.openlibrary.org/b/id/$it-S.jpg"

                Picasso.get()
                    .load(imageUrlL)
                    .into(holder.coverImageView, object : Callback {
                        override fun onSuccess() {
                            holder.coverImageView.setBackgroundColor(Color.TRANSPARENT)
                        }

                        override fun onError(e: Exception?) {
                            Picasso.get()
                                .load(imageUrlM)
                                .into(holder.coverImageView, object : Callback {
                                    override fun onSuccess() {
                                        holder.coverImageView.setBackgroundColor(Color.TRANSPARENT)
                                    }

                                    override fun onError(e: Exception?) {
                                        Picasso.get()
                                            .load(imageUrlS)
                                            .into(holder.coverImageView, object : Callback {
                                                override fun onSuccess() {
                                                    holder.coverImageView.setBackgroundColor(Color.TRANSPARENT)
                                                }

                                                override fun onError(e: Exception?) {
                                                    holder.coverImageView.setBackgroundColor(Color.GRAY)
                                                }
                                            })
                                    }
                                })
                        }
                    })
                holder.imageText.visibility = View.GONE
            }
        }

        checkFavorite(book.key){ isFavorite ->
            if (isFavorite) holder.favoriteIcon.setColorFilter(Color.RED)
            else holder.favoriteIcon.setColorFilter(Color.BLACK)
        }

        holder.card.setOnClickListener{
            checkFavorite(book.key) { isFavorite ->
                if (isFavorite) viewModel.deleteBook(book){
                    holder.favoriteIcon.setColorFilter(Color.BLACK)
                } else viewModel.insertBook(book){
                    holder.favoriteIcon.setColorFilter(Color.RED)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }

     private fun checkFavorite(key: String, callback: (Boolean) -> Unit){
         viewModel.getKey(key).observe(owner, Observer{ item ->
             callback(item != null)
         })
     }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: LinearLayout = itemView.findViewById(R.id.card)
        val titleTextView: TextView = itemView.findViewById(R.id.book_title)
        val authorTextView: TextView = itemView.findViewById(R.id.book_author)
        val yearTextView: TextView = itemView.findViewById(R.id.book_year)
        val coverImageView: ImageView = itemView.findViewById(R.id.book_cover)
        val imageText: TextView = itemView.findViewById(R.id.imageText)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.favorite_icon)
    }
}
