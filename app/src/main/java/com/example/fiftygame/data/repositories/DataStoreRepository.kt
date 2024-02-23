package com.example.fiftygame.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class DataStoreRepository private constructor(context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)
    private val dataStore = context.dataStore

    companion object {
        private const val PREFERENCE_NAME = "player_preferences"

        // Singleton instance
        @Volatile
        private var instance: DataStoreRepository? = null

        fun getInstance(context: Context): DataStoreRepository {
            return instance ?: synchronized(this) {
                instance ?: DataStoreRepository(context).also { instance = it }
            }
        }

        val nameKey = stringPreferencesKey("NAME_KEY")
        val levelKey = intPreferencesKey("LEVEL_KEY")
    }

    suspend fun setName(name: String) {
        dataStore.edit { preference ->
            preference[nameKey] = name
        }
    }

    suspend fun setLevel(level: Int) {
        dataStore.edit { preference ->
            preference[levelKey] = level
        }
    }

    fun getName(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }

        }.map { pref ->
            val playerName = pref[nameKey] ?: "name not found"
            playerName
        }
    }

    fun getLevel(): Flow<Int> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { pref ->
            val playerLevel = pref[levelKey] ?: 1
            playerLevel
        }
    }


}