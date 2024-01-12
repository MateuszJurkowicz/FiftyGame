package com.example.fiftygame.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fields_table")
data class Field (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val question: String,
    val correctAnswer: String
)