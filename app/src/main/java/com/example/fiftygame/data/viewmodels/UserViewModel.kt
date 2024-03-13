package com.example.fiftygame.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.fiftygame.data.Firestore
import com.example.fiftygame.data.models.Player

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val firestore: Firestore = Firestore()
    fun addUser(userId: String, email: String?, name: String?) {
        firestore.addUser(userId, email, name)
    }

    suspend fun readPlayer(email: String?): Player {
        return firestore.readPlayer(email)
    }
}