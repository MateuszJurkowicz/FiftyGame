package com.example.fiftygame.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.fiftygame.activities.accountManagment.SignInActivity
import com.example.fiftygame.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mAuth = Firebase.auth

        Handler(Looper.getMainLooper()).postDelayed({
            if (mAuth.currentUser != null) {
                val choiceIntent = Intent(this, ChoiceActivity::class.java)
                startActivity(choiceIntent)
                finish()
            } else {
                val signInIntent = Intent(this, SignInActivity::class.java)
                startActivity(signInIntent)
                finish()
            }

        }, 2000)

    }

}