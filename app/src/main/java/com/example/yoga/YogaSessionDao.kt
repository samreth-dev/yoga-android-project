package com.example.yoga

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface YogaSessionDao {
    @Insert
    fun addYogaSession(yogaSession: YogaSession)

    @Query("SELECT * FROM YOGASESSION")
    fun getAllYogaSessions(): List<YogaSession>

    fun getTotalBurnedCalories(): Int {
        var totalBurnedCalories = 0
        getAllYogaSessions().forEach {
            totalBurnedCalories += it.burnedCalories
        }
        return totalBurnedCalories
    }
}
