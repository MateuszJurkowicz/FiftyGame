package com.example.fiftygame.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.fiftygame.data.Firestore

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val firestore: Firestore = Firestore()
    fun addUser(userId: String, email: String?, name: String?) {
        firestore.addUser(userId, email, name)
    }
}