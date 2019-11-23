package com.noteamname.androidhackathon.networking

import com.noteamname.androidhackathon.networking.models.Book
import retrofit2.Response
import retrofit2.http.GET


interface FundamentalsService {
    @GET("books/")
    suspend fun getBooks(): Response<List<Book>>
}