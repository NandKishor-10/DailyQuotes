package com.nandkishor.dailyquotes

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class QuoteRepository() {

    val client = HttpClient{
        install(ContentNegotiation){
            json(Json{ ignoreUnknownKeys = true })
        }
    }

//    suspend fun fetchQuotes(): List<QuoteResponseItem> {
//        return try {
//            client.get("http://api.quotable.io/quotes/random?limit=20").body()
//        } catch (e: Exception) {
//            Log.e("Log fetchQuotes()", "fetchQuotes: ${e.localizedMessage}")
//            throw Exception ("Failed fetchQuotes() : ${e.localizedMessage}")
//        }
//    }

    suspend fun fetchQuote(): QuoteResponseItem {
        Log.i("Log fetchQuoteOfTheDay()", "fetchQuoteOfTheDay()")
        return try {
            client.get("http://api.quotable.io/random").body()
        } catch (e: Exception) {
            Log.e("Log fetchQuoteOfTheDay()", "fetchQuoteOfTheDay: ${e.localizedMessage}")
            throw Exception ("Failed in fetchQuoteOfTheDay() : ${e.localizedMessage}")
        }
    }
}
