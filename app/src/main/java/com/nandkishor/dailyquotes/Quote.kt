package com.nandkishor.dailyquotes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Quote(
    @PrimaryKey
    var id: String,
    var author: String,
    var content: String,
    var dateAdded: String,
    var tags: List<String>,
    var insertedTime: Date
)