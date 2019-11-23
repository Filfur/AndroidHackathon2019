package com.noteamname.androidhackathon.networking.models

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: Int,
    val title: String,
    val text: List<String>
)