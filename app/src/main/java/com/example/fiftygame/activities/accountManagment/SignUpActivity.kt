package com.example.fiftygame.activities.accountManagment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fiftygame.data.viewmodels.UserViewModel
import com.example.fiftygame.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mAuth = Firebase.auth

        supportActionBar?.hide()

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.registerButton.setOnClickListener {
            val email = binding.signUpEmailEditText.text.toString().trim()
            val password = binding.signUpPasswordEditText.text.toString().trim()
            if (checkAllFields(email, password)) {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Konto zarejestrowane pomyślnie!", Toast.LENGTH_SHORT).show()
                        mAuth.currentUser?.let { mUserViewModel.addUser(it.uid, it.email, it.displayName) }
                        finish()
                    } else {
                        Toast.makeText(this, "Coś poszło nie tak!", Toast.LENGTH_SHORT).show()
                        Log.e("error: ", it.exception.toString())
                    }
                }
            }
        }
    }

    private fun checkAllFields(email: String, password: String): Boolean {
        val confirmPassword = binding.signUpConfirmPasswordEditText.text.toString()

        if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.signUpEmailTextView.error = null
        } else {
            binding.signUpEmailTextView.error = "Check email format"
            return false
        }

        if (password.isNotEmpty() && password.length > 6) {
            binding.signUpPasswordTextView.error = null
        } else {
            if (password.isEmpty()) {
                binding.signUpPasswordTextView.error = "This is required field"
            } else {
                binding.signUpPasswordTextView.error = "Password should be at least 6 characters long"
            }
            return false
        }

        if (confirmPassword.isNotEmpty() && confirmPassword == password) {
            binding.signUpConfirmPasswordTextView.error = null
        } else {
            if (confirmPassword.isEmpty()) {
                binding.signUpConfirmPasswordTextView.error = "This is required field"
            } else {
                binding.signUpConfirmPasswordTextView.error = "Passwords do not match"
            }
            return false
        }

        return true
    }

}