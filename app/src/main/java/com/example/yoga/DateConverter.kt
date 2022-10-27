package com.example.yoga

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateConverter {
    var df = DateTimeFormatter.ISO_DATE_TIME

    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return if (value != null) {
            try {
                return LocalDateTime.parse(value, df)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            null
        } else {
            null
        }
    }

    @TypeConverter
    fun toTimestamp(value: LocalDateTime?): String? {
        return if (value != null) {
            value.format(df)
        } else {
            null
        }
    }
}