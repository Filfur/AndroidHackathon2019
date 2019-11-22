package com.noteamname.androidhackathon.toiletRoll

import androidx.lifecycle.ViewModel
import com.noteamname.androidhackathon.toiletRoll.models.RollPiece
import com.noteamname.androidhackathon.toiletRoll.models.RollPieceHelper

class ToiletRollViewModel: ViewModel() {
    companion object {
        const val PAGE_SIZE = 10
    }

    fun getRollPieces(page: Int): List<RollPiece> {
        return RollPieceHelper.getPeaces()
    }
}