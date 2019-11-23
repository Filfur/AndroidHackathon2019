package com.noteamname.androidhackathon.toiletRoll.viewModels

import androidx.lifecycle.*
import com.noteamname.androidhackathon.networking.NetworkingService
import com.noteamname.androidhackathon.networking.models.Book
import com.noteamname.androidhackathon.toiletRoll.models.RollPiece
import com.noteamname.androidhackathon.toiletRoll.models.RollPieceHelper
import kotlinx.coroutines.launch

class ToiletRollViewModel(
    private val networkingService: NetworkingService
): ViewModel() {

    val livePieces = MutableLiveData<List<RollPiece>>()

    val liveBooks = MutableLiveData<List<Book>>()

//    init {
//        fetchBooks()
//    }

    fun selectBook(book: Book) {
        livePieces.postValue(book.text.map { RollPiece(it) })
    }

    fun fetchBooks() {
        viewModelScope.launch {
            val books = networkingService.getBooks()
            liveBooks.postValue(books)
        }
    }
}