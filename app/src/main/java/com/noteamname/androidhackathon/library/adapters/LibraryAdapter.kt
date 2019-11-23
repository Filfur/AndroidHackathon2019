package com.noteamname.androidhackathon.library.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noteamname.androidhackathon.R
import com.noteamname.androidhackathon.networking.models.Book
import kotlinx.android.synthetic.main.fragment_library_item.view.*

class LibraryAdapter(
    private val listener: LibraryAdapterListener
) : RecyclerView.Adapter<LibraryAdapter.BookViewHolder>() {

    private var books: List<Book> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_library_item,
                parent,
                false
            )
        )
    }

    fun updateItems(items: List<Book>) {
        books = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onBookSelected(books[holder.adapterPosition])
        }
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = books.size

    private fun getItem(position: Int): Book = books[position]

    class BookViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.title
        private val image: ImageView = itemView.image_toilet_roll

        fun bind(book: Book) {
            title.text = book.title
            val color = listOf(R.drawable.book_item_green, R.drawable.book_item_yellow, R.drawable.book_item_white, R.drawable.book_item_purple).random()
            Glide.with(itemView).load(color).into(image)

        }
    }

    interface LibraryAdapterListener {
        fun onBookSelected(book: Book)
    }
}
