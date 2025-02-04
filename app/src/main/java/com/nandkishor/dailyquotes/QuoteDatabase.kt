package com.nandkishor.dailyquotes

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Quote::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class QuoteDatabase: RoomDatabase() {
    companion object {
        const val NAME = "Quote_DB"
    }

    abstract fun getQuoteDao(): QuoteDao
}