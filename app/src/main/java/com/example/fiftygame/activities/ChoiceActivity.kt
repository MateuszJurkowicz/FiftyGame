package com.example.fiftygame.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fiftygame.R
import com.example.fiftygame.activities.accountManagment.SignInActivity
import com.example.fiftygame.databinding.ActivityChoiceBinding
import com.example.fiftygame.databinding.ActivityMainBinding
import com.example.fiftygame.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class ChoiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChoiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoiceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.moveToJoinGameButton.setOnClickListener {
            val joinGameIntent = Intent(this, JoinGameActivity::class.java)
            startActivity(joinGameIntent)
        }
        binding.moveToCreateGameButton.setOnClickListener {
            val createGameIntent = Intent(this, CreateGamesActivity::class.java)
            startActivity(createGameIntent)
        }
    }


    override fun onResume() {
        super.onResume()
        clearBackStackIfNeeded()
    }

    private fun clearBackStackIfNeeded() {
        if (intent?.extras?.getBoolean("sign_out") == true) {
            val intent = Intent(this, ChoiceActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

    }
}