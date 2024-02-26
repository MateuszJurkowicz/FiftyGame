package com.example.fiftygame.activities.accountManagment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.fiftygame.databinding.ActivityResetPasswordBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mAuth = Firebase.auth

        supportActionBar?.hide()

        binding.confirmButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            if (checkEmail(email)) {
                mAuth.sendPasswordResetEmail(email).addOnSuccessListener {
                    Toast.makeText(this, "Wysłano wiadomość na podany adres email", Toast.LENGTH_LONG).show()
                }
                    .addOnFailureListener {
                        Log.e("error: ", it.toString())
                        Toast.makeText(this, "Coś poszło nie tak", Toast.LENGTH_LONG).show()
                    }

            }
        }
    }

    private fun checkEmail(email: String): Boolean {
        if (email.isEmpty()) {
            binding.emailTextView.error = "This field is required"
            return false
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailTextView.error = "Check email format"
                return false
            } else {
                binding.emailTextView.error = null
            }
        }
        return true
    }
}