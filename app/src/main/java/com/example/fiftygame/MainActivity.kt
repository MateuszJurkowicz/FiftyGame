package com.example.fiftygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var joinGameButton: Button
    lateinit var createGameButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        joinGameButton = findViewById(R.id.moveToJoinGameButton)
        createGameButton = findViewById(R.id.moveToCreateGameButton)
        joinGameButton.setOnClickListener{
            val intent = Intent(this@MainActivity, JoinGameActivity::class.java)
            startActivity(intent)
        }
        createGameButton.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateGameActivity::class.java)
            startActivity(intent)
        }

    }
}