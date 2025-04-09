package com.example.quoteapp.data.repository

import android.util.Log
import com.example.quoteapp.data.model.toDomain
import com.example.quoteapp.data.remote.ApiService
import com.example.quoteapp.domain.model.Quote
import com.example.quoteapp.domain.repository.QuoteRepository

class QuoteRepositoryImpl(private val apiService: ApiService) : QuoteRepository {
    private var quotesCache: List<Quote> = emptyList()

    override suspend fun getAllQuotes(): List<Quote> {
        if (quotesCache.isEmpty()) {
            try {
                val response = apiService.getAllQuotes()
                if (response.data.isEmpty()) {
                    throw Exception("Цитати не знайдено")
                }
                quotesCache = response.data.map { it.toDomain() }

            } catch (e: Exception) {
                throw e
            }
        }
        return quotesCache
    }
}
