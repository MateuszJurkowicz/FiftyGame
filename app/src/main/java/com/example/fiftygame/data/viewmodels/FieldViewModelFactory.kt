package com.example.fiftygame.data.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class FieldViewModelFactory(private val application: Application, private var gameId: Int) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FieldViewModel::class.java)) {
            return FieldViewModel(application, gameId) as T
        }
        throw IllegalArgumentException("FieldViewModel class not found");
    }
}