package com.nandkishor.dailyquotes

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class QuoteViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    private val _quote = MutableStateFlow<QuoteResponseItem?>(null)
    val quote: StateFlow<QuoteResponseItem?> = _quote.asStateFlow()

    init {
        Log.i("Log QuoteViewModel()", "Init")
        loadQuote()
    }

    @SuppressLint("NewApi")
    fun loadQuote() {
        viewModelScope.launch {
            Log.i("Log loadQuote()", "viewModelScope")
            try {
                Log.d("Log loadQuote()", "-try ${_quote.value}, ${quote.value}")
                _quote.value = quoteRepository.fetchQuote()
                Log.d("Log loadQuote()", "try- ${_quote.value}, ${quote.value}")
                val newQuote = _quote.value
                newQuote?.let {
                    insertQuote(
                        it.id,
                        it.author,
                        it.content,
                        it.dateAdded,
                        it.tags
                    )
                }
            } catch (e: Exception) {
                Log.e("Log loadQuote()", "viewModelScope: ${e.toString()}")
            }
        }
    }


//    fun notify(applicationContext: Context) {
//        viewModelScope.launch {
//            Log.i("Log Fetching Quote Of The Day", "viewModelScope")
//            try {
//                val quoteOfTheDay = quoteRepository.fetchQuoteOfTheDay()
//                Log.d("Log notify()", "author: ${quoteOfTheDay[0].author},\n content: ${quoteOfTheDay[0].content}")
//                sendNotification(quoteOfTheDay[0].author, quoteOfTheDay[0].content, applicationContext)
//            } catch (e: Exception) {
//                Log.e("Log notify()", "viewModelScope: ${e.toString()}")
//            }
//        }
//    }

    private val quoteDao = QuoteApplication.quoteDatabase.getQuoteDao()
    val quoteList: StateFlow<List<Quote>> = quoteDao.getAllQuotes()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertQuote(id: String, author: String, content: String, dateAdded: String, tags: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            quoteDao.insertQuote(Quote(id, author, content, dateAdded, tags, Date.from(Instant.now())))
        }
    }

    fun deleteQuote(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            quoteDao.deleteQuote(id)
        }
    }

}