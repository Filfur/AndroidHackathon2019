package com.noteamname.androidhackathon.toiletRoll.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noteamname.androidhackathon.toiletRoll.models.RollPiece
import com.noteamname.androidhackathon.toiletRoll.models.RollPieceHelper

class ToiletRollViewModel: ViewModel() {
    companion object {
        const val PAGE_SIZE = 4
    }

    val livePieces = MutableLiveData<List<RollPiece>>()

    fun fetchRollPieces(page: Int) {
        if ((page + 1) * PAGE_SIZE > RollPieceHelper.getPeaces().count()) {
            return
        }
        val pieces = RollPieceHelper.getPeaces().subList(page * PAGE_SIZE, (page + 1) * PAGE_SIZE)

        livePieces.postValue(pieces)
    }
}