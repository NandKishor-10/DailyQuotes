package com.nandkishor.dailyquotes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuoteViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {
    private val _quotes = MutableStateFlow<List<QuoteResponseItem>>(emptyList())
    val quotes: StateFlow<List<QuoteResponseItem>> = _quotes

    private val _quote = MutableStateFlow<QuoteResponseItem?>(null)
    val quote: StateFlow<QuoteResponseItem?> = _quote.asStateFlow()

    init {
        Log.i("Log QuoteViewModel()", "Init")
        //loadQuotes()
        loadQuote()
    }

    fun loadQuote() {
        viewModelScope.launch {
            Log.i("Log loadQuote()", "viewModelScope")
            try {
                Log.i("Log loadQuote()", "try ${_quote.value}, ${quote.value}")
                _quote.value = quoteRepository.fetchQuoteOfTheDay()
                Log.i("Log loadQuote()", "try ${_quote.value}, ${quote.value}")
            } catch (e: Exception) {
                Log.e("Log loadQuote()", "viewModelScope: ${e.toString()}")
            }
        }
    }

    private fun loadQuotes() {
        viewModelScope.launch {
            Log.i("Log loadQuotes()", "viewModelScope")
            try {
                _quotes.value = quoteRepository.fetchQuotes()
            } catch (e: Exception) {
                Log.e("Log loadQuotes()", "viewModelScope: ${e.toString()}")
            }
        }
    }

//    fun notify(applicationContext: Context) {
//        viewModelScope.launch {
//            Log.i("Log Fetching Quote Of The Day", "viewModelScope")
//            try {
//                val quoteOfTheDay = quoteRepository.fetchQuoteOfTheDay()
//                Log.d("Log notify()", "author: ${quoteOfTheDay[0].author},\ncontent: ${quoteOfTheDay[0].content}")
//                sendNotification(quoteOfTheDay[0].author, quoteOfTheDay[0].content, applicationContext)
//            } catch (e: Exception) {
//                Log.e("Log notify()", "viewModelScope: ${e.toString()}")
//            }
//        }
//    }
}