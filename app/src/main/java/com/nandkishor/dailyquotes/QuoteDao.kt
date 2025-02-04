package com.nandkishor.dailyquotes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM Quote ORDER BY insertedTime DESC")
    fun getAllQuotes(): Flow<List<Quote>>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insertQuote(quote: Quote)

    @Query("DELETE FROM Quote WHERE id = :id")
    suspend fun deleteQuote(id: String)
}