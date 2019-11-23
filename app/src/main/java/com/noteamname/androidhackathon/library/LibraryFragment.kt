package com.noteamname.androidhackathon.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noteamname.androidhackathon.R
import com.noteamname.androidhackathon.library.adapters.LibraryAdapter
import com.noteamname.androidhackathon.networking.models.Book
import com.noteamname.androidhackathon.toiletRoll.viewModels.ToiletRollViewModel
import kotlinx.android.synthetic.main.fragment_library.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LibraryFragment : Fragment(), LibraryAdapter.LibraryAdapterListener {

    val viewModel: ToiletRollViewModel by sharedViewModel()

    private val adapter: LibraryAdapter by lazy {
        LibraryAdapter(this)
    }

    private val navController: NavController by lazy {
        findNavController()
    }

    private val booksObserver = Observer<List<Book>> { books ->
        image_loading.visibility = if (books.count() > 0) View.GONE else View.VISIBLE
        adapter.updateItems(books)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchBooks()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveBooks.observe(viewLifecycleOwner, booksObserver)
    }

    override fun onBookSelected(book: Book) {
        viewModel.selectBook(book)
        navController.navigate(LibraryFragmentDirections.actionToiletRoll())
    }
}
