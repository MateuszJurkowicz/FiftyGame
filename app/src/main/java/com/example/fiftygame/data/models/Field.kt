package com.example.fiftygame.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Field(
    var fieldId: String,
    val number: Int,
    val entry: String,
    val question: String,
    val correctAnswer: String,
) : Parcelable {
    constructor() : this("0", 0, "hasło", "pytanie", "okoń")
}