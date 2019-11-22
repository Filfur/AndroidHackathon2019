package com.noteamname.androidhackathon.library.models

object BooksStorage {

    fun getBooksList(): List<Book> {
        return listOf(
            Book(
                "Jurassic World - Fallen Kingdom"
            ),

            Book(
                "The Meg"
            ),

            Book(
                "The First Purge"
            ),

            Book(
                "Deadpool 2"
            ),

            Book(
                "Black Panther"
            ),

            Book(
                "Ocean's Eight"
            ),

            Book(
                "Interstellar"
            ),

            Book(
                "Thor - Ragnarok"
            ),

            Book(
                "Guardians of the Galaxy"
            )
        )
    }
}
