package com.example.yoga

import androidx.room.TypeConverter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    var df: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return if (value != null) {
            try {
                return df.parse(value)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            null
        } else {
            null
        }
    }

    @TypeConverter
    fun toTimestamp(value: Date?): String? {
        return if (value != null) {
            df.format(value)
        } else {
            null
        }
    }
}