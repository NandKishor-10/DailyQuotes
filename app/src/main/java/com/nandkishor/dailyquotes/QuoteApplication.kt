package com.nandkishor.dailyquotes

import android.app.Application
import androidx.room.Room

class QuoteApplication: Application() {
    companion object {
        lateinit var quoteDatabase: QuoteDatabase
    }

    override fun onCreate() {
        super.onCreate()
        quoteDatabase = Room.databaseBuilder(
            applicationContext,
            QuoteDatabase::class.java,
            QuoteDatabase.NAME
        ).build()
    }
}