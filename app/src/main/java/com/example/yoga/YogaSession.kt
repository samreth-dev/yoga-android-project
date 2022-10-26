package com.example.yoga

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class YogaSession(
    @PrimaryKey var id: Int,
    var burnedCalories: Int,
    var duration: Int,
    var yogaCompletionDateTime: String
)
