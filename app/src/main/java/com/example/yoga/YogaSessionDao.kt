package com.example.yoga

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Dao
interface YogaSessionDao {
    @Insert
    fun addYogaSession(yogaSession: YogaSession)

    @Query("SELECT * FROM YOGASESSION")
    fun getAllYogaSessions(): List<YogaSession>

    @Query("SELECT SUM(burnedCalories) FROM YOGASESSION WHERE yogaCompletionDateTime BETWEEN :date AND :dateEnd")
    fun getTotalBurnedCalories(date: LocalDateTime, dateEnd: LocalDateTime): Double

    @Query("SELECT SUM(duration) FROM YOGASESSION WHERE yogaCompletionDateTime BETWEEN :date AND :dateEnd")
    fun getTotalYogaDuration(date: LocalDateTime, dateEnd: LocalDateTime): Double
}
