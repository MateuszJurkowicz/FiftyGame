package com.example.fiftygame.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Parcelize
@Entity(tableName = "fields_table")
data class Field (
    @PrimaryKey(autoGenerate = true)
    val fieldId: Int,
    val number: Int,
    val entry: String,
    val question: String,
    val correctAnswer: String,
    val ownerGameId: Int
) : Parcelable