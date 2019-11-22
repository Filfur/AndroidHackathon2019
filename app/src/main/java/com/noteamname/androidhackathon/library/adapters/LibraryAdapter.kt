package com.noteamname.androidhackathon.library.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.noteamname.androidhackathon.R
import com.noteamname.androidhackathon.library.models.Book

class LibraryAdapter(
    private val books: List<Book>,
    private val listener: LibraryAdapterListener
) : RecyclerView.Adapter<LibraryAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_library_item,
                parent,
                false
            )
        )
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

        private val title: TextView = itemView.findViewById(R.id.title)

        fun bind(book: Book) {
            title.text = book.title
        }
    }

    interface LibraryAdapterListener {
        fun onBookSelected(book: Book)
    }
}
