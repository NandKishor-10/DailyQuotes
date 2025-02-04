package com.nandkishor.dailyquotes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteResponseItem(
    @SerialName("_id") val id: String,
    @SerialName("author") val author: String,
    @SerialName("content") val content: String,
    @SerialName("dateAdded") val dateAdded: String,
    @SerialName("tags") val tags: List<String>
)