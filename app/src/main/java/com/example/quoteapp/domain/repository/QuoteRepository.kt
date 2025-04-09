package com.example.quoteapp.domain.repository

import com.example.quoteapp.domain.model.Quote

interface QuoteRepository {
    suspend fun getAllQuotes(): List<Quote>
}