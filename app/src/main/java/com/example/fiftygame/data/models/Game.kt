package com.example.fiftygame.data.models;

import android.os.Parcelable
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "games_table")
data class Game(
    @PrimaryKey(autoGenerate = true)
    val gameId: Int,
    val pin: Int,
    val description: String

) : Parcelable