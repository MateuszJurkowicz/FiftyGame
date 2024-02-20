package com.example.fiftygame.data.models;

import android.os.Parcelable
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    var gameId: String?,
    val pin: Int,
    val description: String?,
    val ownerEmail: String?

) : Parcelable {
    constructor() : this("0", 0, "", "")
}
