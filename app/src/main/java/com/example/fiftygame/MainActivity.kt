package com.example.fiftygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fiftygame.create.CreateGameActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var joinGameButton: Button
    private lateinit var createGameButton: Button
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        joinGameButton = findViewById(R.id.moveToJoinGameButton)
        createGameButton = findViewById(R.id.moveToCreateGameButton)
        joinGameButton.setOnClickListener {
            val joinGameIntent = Intent(this, JoinGameActivity::class.java)
            startActivity(joinGameIntent)
        }
        createGameButton.setOnClickListener {
            if (user != null) {
                val createGameIntent = Intent(this, CreateGameActivity::class.java)
                startActivity(createGameIntent)
                finish()
            } else {
                val signInIntent = Intent(this, SignInActivity::class.java)
                startActivity(signInIntent)
                finish()
            }
        }

    }
}