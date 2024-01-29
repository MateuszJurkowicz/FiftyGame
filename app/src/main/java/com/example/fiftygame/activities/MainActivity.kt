package com.example.fiftygame.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fiftygame.R
import com.example.fiftygame.databinding.ActivityMainBinding
import com.example.fiftygame.databinding.FragmentAddFieldBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        binding.moveToJoinGameButton.setOnClickListener {
            val joinGameIntent = Intent(this, JoinGameActivity::class.java)
            startActivity(joinGameIntent)
        }
        binding.moveToCreateGameButton.setOnClickListener {
            if (user != null) {
                val createGameIntent = Intent(this, CreateGamesActivity::class.java)
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