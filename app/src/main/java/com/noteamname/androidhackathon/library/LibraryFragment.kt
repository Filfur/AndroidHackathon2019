package com.noteamname.androidhackathon.library

import LibraryAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noteamname.androidhackathon.R
import com.noteamname.androidhackathon.library.models.BooksStorage

class LibraryFragment : Fragment() {

    private val adapter: LibraryAdapter = LibraryAdapter(BooksStorage.getBooksList()) { position ->
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_library, container, false)

        view.findViewById<RecyclerView>(R.id.booksList).apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = this@LibraryFragment.adapter
        }

        return view
    }
}
