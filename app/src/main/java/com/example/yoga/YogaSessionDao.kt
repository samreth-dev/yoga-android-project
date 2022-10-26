package com.example.yoga

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface YogaSessionDao {
    @Insert
    fun addYogaSession(yogaSession: YogaSession)

    @Query("SELECT * FROM YOGASESSION")
    fun getAllYogaSessions(): List<YogaSession>

    @Query("SELECT SUM(burnedCalories) FROM YOGASESSION WHERE yogaCompletionDateTime = :date")
    fun getTotalBurnedCalories(date: Date): Int

    @Query("SELECT SUM(duration) FROM YOGASESSION WHERE yogaCompletionDateTime = :date")
    fun getTotalYogaDuration(date: Date): Int
}
