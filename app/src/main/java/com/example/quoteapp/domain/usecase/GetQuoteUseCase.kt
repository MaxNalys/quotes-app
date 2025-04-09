package com.example.quoteapp.domain.usecase

import com.example.quoteapp.domain.repository.QuoteRepository
import com.example.quoteapp.domain.model.Quote

class GetQuoteUseCase(private val quoteRepository: QuoteRepository) {
    suspend fun execute(): List<Quote> {
        return quoteRepository.getAllQuotes()
    }
}