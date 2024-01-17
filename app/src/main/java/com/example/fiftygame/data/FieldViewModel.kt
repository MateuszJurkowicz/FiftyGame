package com.example.fiftygame.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FieldViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Field>>
    private val repository: FieldRepository
    private val firestore: Firestore = Firestore()

    init {
        val fieldDao = FieldDatabase.getDatabase(application).fieldDao()
        repository = FieldRepository(fieldDao)
        readAllData = repository.readAllData
    }

    fun addField(field: Field) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addField(field)
            firestore.addField(field)
        }
    }

    fun updateField(field: Field) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateField(field)
        }
    }

    fun deleteField(field: Field) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteField(field)
        }
    }
    fun deleteAllFields() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFields()
        }
    }
    fun searchDatabase(searchQuery: String): LiveData<List<Field>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}