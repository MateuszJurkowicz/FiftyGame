package com.example.fiftygame.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fiftygame.data.repositories.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStore = DataStoreRepository.getInstance(application)

    val getName = dataStore.getName().asLiveData(Dispatchers.IO)
    val getLevel = dataStore.getLevel().asLiveData(Dispatchers.IO)
    fun setName(name: String) {
        viewModelScope.launch {
            dataStore.setName(name)
        }
    }

    fun setLevel(level: Int) {
        viewModelScope.launch {
            dataStore.setLevel(level)
        }
    }
    fun sumLevel(add: Int) {
        viewModelScope.launch {
            dataStore.sumLevel(add)
        }
    }
}