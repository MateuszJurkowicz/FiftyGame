package com.example.fiftygame.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Field(
    var fieldId: String,
    val number: Int,
    val entry: String,
    val question: String,
    val correctAnswer: String,
    var keywords: List<String>,
) : Parcelable {
    constructor() : this("0", 0, "hasło", "pytanie", "okoń", listOf())
}