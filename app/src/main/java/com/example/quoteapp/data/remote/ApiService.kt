package com.example.quoteapp.data.remote

import com.example.quoteapp.data.model.QuoteResponse
import retrofit2.http.GET

interface ApiService {
    @GET("quotes")
    suspend fun getAllQuotes(): QuoteResponse
}