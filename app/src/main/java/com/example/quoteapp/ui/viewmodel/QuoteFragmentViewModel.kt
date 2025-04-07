package com.example.quoteapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteapp.domain.usecase.GetQuoteUseCase
import com.example.quoteapp.domain.model.Quote
import kotlinx.coroutines.launch

class QuoteFragmentViewModel(private val getQuoteUseCase: GetQuoteUseCase) : ViewModel() {

    private val _quoteLiveData = MutableLiveData<Quote>()
    val quoteLiveData: LiveData<Quote> get() = _quoteLiveData
    private val _loadingLiveData = MutableLiveData<Boolean>()
    private val _errorLiveData = MutableLiveData<String>()
    private var allQuotes: List<Quote> = emptyList()
    private var currentIndex = 0

    fun loadAllQuotes() {
        _loadingLiveData.value = true
        viewModelScope.launch {
            try {
                allQuotes = getQuoteUseCase.execute()
                currentIndex = 0
                _loadingLiveData.value = false

                if (allQuotes.isNotEmpty()) {
                    _quoteLiveData.value = allQuotes[currentIndex]
                } else {
                    _errorLiveData.value = "Цитати не знайдено."
                }
            } catch (e: Exception) {
                _loadingLiveData.value = false
                _errorLiveData.value = "Помилка при завантаженні цитат: ${e.message}"
            }
        }
    }

    fun showNextQuote() {
        if (allQuotes.isNotEmpty()) {
            currentIndex = (currentIndex + 1) % allQuotes.size
            _quoteLiveData.postValue(allQuotes[currentIndex])
        }
    }
}
