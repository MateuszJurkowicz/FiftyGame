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
            } else {
                val signInIntent = Intent(this, SignInActivity::class.java)
                startActivity(signInIntent)
            }
        }

    }
    override fun onResume() {
        super.onResume()
        clearBackStackIfNeeded()
    }

    private fun clearBackStackIfNeeded() {
        if (intent?.extras?.getBoolean("sign_out") == true) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

}