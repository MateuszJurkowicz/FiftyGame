package com.example.fiftygame.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    var email: String?,
    val name: String?,
    var userId: String?,
    var level: Int

) : Parcelable {
    constructor() : this("", "", "", 1)
}
