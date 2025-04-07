package com.example.quoteapp.data.model

import com.example.quoteapp.domain.model.Quote

data class QuoteDto(
    val id: Int,
    val quote: String,
    val author: String,
    val category: String
)
data class QuoteResponse(
    val data: List<QuoteDto>
)

fun QuoteDto.toDomain(): Quote {
    return Quote(id, quote, author, category)
}