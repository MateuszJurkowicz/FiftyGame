package com.example.fiftygame.data.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.fiftygame.data.Firestore
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.data.models.Player
import com.example.fiftygame.data.repositories.FieldRepository
import com.example.fiftygame.data.repositories.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository
    private val firestore: Firestore = Firestore()
    //lateinit var player: Player

    init {
        userRepository = UserRepository(firestore)
        /*(viewModelScope.launch {
            player = withContext(Dispatchers.IO) {
                userRepository.readPlayerInGame(currentGame, FirebaseAuth.getInstance().currentUser?.uid)!!
            }
            Log.d("PlayerViewModel: ", "$player")
        })*/
    }


    fun addUser(userId: String, email: String?, name: String?) {
        userRepository.addUser(userId, email, name)
    }

    suspend fun readPlayer(email: String?): Player {
        return userRepository.readPlayer(email)
    }

    suspend fun readPlayerInGame(currentGame: Game) :Player? {
        return userRepository.readPlayerInGame(currentGame, FirebaseAuth.getInstance().currentUser?.uid)
    }

    /*fun getLevel(playerId: String?, currentGame: Game): LiveData<Int> {
        val level = MutableLiveData<Int>()
        viewModelScope.launch(Dispatchers.IO) {
            val returnedLevel = firestore.getLevel(playerId, currentGame)
            level.postValue(returnedLevel)
        }
        return level
    }*/

    fun setLevel(playerId: String?, currentGame: Game, newLevel: Int) {
        firestore.setLevel(playerId, currentGame, newLevel)
    }
}