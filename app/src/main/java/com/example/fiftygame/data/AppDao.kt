package com.example.fiftygame.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.fiftygame.data.models.Field
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.data.relations.GameWithFields
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addField(field: Field)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addGame(game: Game)

    @Update
    suspend fun updateField(field: Field)

    @Update
    suspend fun updateGame(game: Game)

    @Delete
    suspend fun deleteField(field: Field)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM fields_table")
    suspend fun deleteAllFields()

    @Query("SELECT * FROM games_table ORDER BY gameId ASC")
    fun readAllGames(): LiveData<List<Game>>

    // live data moze do zmiany na samo List<Field>
    @Transaction
    @Query("SELECT * FROM games_table WHERE gameId = :gameId")
    fun readGameWithFields(gameId: Int): LiveData<List<GameWithFields>>


    @Query("SELECT * FROM fields_table WHERE entry LIKE :searchQuery OR question LIKE :searchQuery OR correctAnswer LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Field>>
}