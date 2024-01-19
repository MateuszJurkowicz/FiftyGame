package com.example.fiftygame.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Game

class CreateGamesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_games)
    }

    fun editGame(game: Game) {
        val intent = Intent(this, CreateFieldsActivity::class.java)
        intent.putExtra("game ID", game.gameId)
        startActivity(intent)
    }
}