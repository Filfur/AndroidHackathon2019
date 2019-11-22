package com.noteamname.androidhackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.noteamname.androidhackathon.library.LibraryFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val books = BooksStorage.getBooksList()
        // val adapter = BooksAdapter(this, books) { position ->
        //     showBookFragment(books, position)
        // }

        // val list = findViewById<RecyclerView>(R.id.moviesList)
        // list.adapter = adapter
        // list.layoutManager = GridLayoutManager(this, 3)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.container, LibraryFragment())
            .commit()
    }
}
