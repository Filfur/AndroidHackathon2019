package com.noteamname.androidhackathon.library.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val title: String
) : Parcelable
