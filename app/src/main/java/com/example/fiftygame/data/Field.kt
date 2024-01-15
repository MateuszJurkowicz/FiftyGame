package com.example.fiftygame.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Parcelize
@Entity(tableName = "fields_table")
data class Field (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val entry: String,
    val question: String,
    val correctAnswer: String
) : Parcelable