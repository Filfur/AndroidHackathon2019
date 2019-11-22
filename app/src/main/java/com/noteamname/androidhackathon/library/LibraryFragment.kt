package com.noteamname.androidhackathon.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noteamname.androidhackathon.R
import com.noteamname.androidhackathon.library.adapters.LibraryAdapter
import com.noteamname.androidhackathon.library.models.Book
import com.noteamname.androidhackathon.library.models.BooksStorage

class LibraryFragment : Fragment(), LibraryAdapter.LibraryAdapterListener {

    private val adapter: LibraryAdapter by lazy {
        LibraryAdapter(BooksStorage.getBooksList(), this)
    }

    private val navController: NavController by lazy {
        findNavController()
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

    override fun onBookSelected(book: Book) {
        navController.navigate(LibraryFragmentDirections.actionToiletRoll())
    }
}
