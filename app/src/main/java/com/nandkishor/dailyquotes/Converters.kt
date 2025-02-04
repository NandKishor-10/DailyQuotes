package com.nandkishor.dailyquotes

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromList(list: List<String>?) = gson.toJson(list)

    @TypeConverter
    fun toList(value: String?): List<String>? {
        if (value.isNullOrEmpty()) return emptyList()
        val listType = object: TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromDate(date: Date) = date.time

    @TypeConverter
    fun toDate(time: Long) = Date(time)


}