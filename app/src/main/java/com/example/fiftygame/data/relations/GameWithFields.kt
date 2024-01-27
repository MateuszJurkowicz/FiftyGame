package com.example.fiftygame.data.relations

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.Game

data class GameWithFields (
    @Embedded val game: Game,
    @Relation(
        parentColumn = "gameId",
        entityColumn = "gameId"
    )
    var fields: List<Field>
)