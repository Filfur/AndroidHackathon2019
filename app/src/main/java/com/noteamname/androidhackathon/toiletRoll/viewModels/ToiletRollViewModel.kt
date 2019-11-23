package com.noteamname.androidhackathon.toiletRoll.viewModels

import androidx.lifecycle.*
import com.noteamname.androidhackathon.networking.NetworkingService
import com.noteamname.androidhackathon.networking.models.Book
import com.noteamname.androidhackathon.toiletRoll.models.RollPiece
import com.noteamname.androidhackathon.toiletRoll.models.RollPieceHelper
import kotlinx.coroutines.launch

class ToiletRollViewModel(
    val networkingService: NetworkingService
): ViewModel() {
    companion object {
        const val PAGE_SIZE = 4
    }

    private var pageCount = -1

    var books: LiveData<List<Book>> = liveData {
        emit(networkingService.getBooks())
    }

    val livePieces = MutableLiveData<List<RollPiece>>()

    init {
        fetchRollPieces()
    }

    fun fetchRollPieces() {
        viewModelScope.launch {
            //        ++pageCount
//        if ((pageCount + 1) * PAGE_SIZE > RollPieceHelper.getPeaces().count()) {
//            return
//        }
//        val pieces = RollPieceHelper.getPeaces().subList(pageCount * PAGE_SIZE, (pageCount + 1) * PAGE_SIZE)
            val books = networkingService.getBooks()
            val pieces = (books.get(0)?.text ?: listOf()).map { RollPiece(it) }
            livePieces.postValue(pieces)
        }
    }
}