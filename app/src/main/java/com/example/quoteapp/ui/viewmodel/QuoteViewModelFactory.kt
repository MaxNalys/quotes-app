package com.example.quoteapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quoteapp.domain.usecase.GetQuoteUseCase

class QuoteViewModelFactory(
    private val getQuoteUseCase: GetQuoteUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(QuoteFragmentViewModel::class.java) -> {
                QuoteFragmentViewModel(getQuoteUseCase) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
