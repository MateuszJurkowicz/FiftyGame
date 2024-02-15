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
    suspend fun addField(field: Field)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGame(game: Game)

    @Update
    suspend fun updateField(field: Field)

    @Update
    suspend fun updateGame(game: Game)

    @Delete
    suspend fun deleteField(field: Field)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM fields_table WHERE ownerGameId = :gameId")
    suspend fun deleteFieldsInGame(gameId: Int)

    @Query("DELETE FROM fields_table")
    suspend fun deleteAllFields()

    @Query("SELECT * FROM games_table ORDER BY gameId ASC")
    fun readAllGames(): LiveData<List<Game>>

    @Transaction
    @Query("SELECT * FROM games_table WHERE  gameId = :gameId")
    fun readGameWithId(gameId: Int): Game

    @Transaction
    @Query("SELECT * FROM games_table WHERE gameId = :gameId")
    fun readGameWithFields(gameId: Int): LiveData<List<GameWithFields>>

    @Transaction
    @Query("SELECT * FROM fields_table WHERE ownerGameId = :gameId AND (entry LIKE :searchQuery OR question LIKE :searchQuery OR correctAnswer LIKE :searchQuery)")
    fun searchDatabase(gameId: Int, searchQuery: String): Flow<List<Field>>

    @Query("SELECT * FROM games_table WHERE pin = :pin")
    fun readGameWithPin(pin: Int): Game


}