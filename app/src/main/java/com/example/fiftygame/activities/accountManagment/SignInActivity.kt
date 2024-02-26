package com.example.fiftygame.activities.accountManagment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fiftygame.R
import com.example.fiftygame.activities.ChoiceActivity
import com.example.fiftygame.activities.CreateGamesActivity
import com.example.fiftygame.data.viewmodels.UserViewModel
import com.example.fiftygame.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth

class SignInActivity : AppCompatActivity() {
    companion object {
        private const val RC_SIGN_IN = 15
    }

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()


        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webId))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        mAuth = Firebase.auth

        binding.googleSignInButton.setOnClickListener {
            signIn()
        }
        binding.signupTextView.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }
        binding.forgotPasswordTextView.setOnClickListener {
            val resetPasswordIntent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(resetPasswordIntent)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.signInEmailEditText.text.toString().trim()
            val password = binding.signInPasswordEditText.text.toString().trim()
            if (checkAllFields(email, password)) {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Pomyślnie zalogowano!", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, ChoiceActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Coś poszło nie tak!", Toast.LENGTH_LONG).show()
                        Log.e("error: ", it.exception.toString())
                    }
                }
            }
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    // Got an ID token from Google. Use it to authenticate
                    // with Firebase.
                    Log.d("SignInActivity", "FirebaseAuthWithGoogle: " + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    Log.w("SignInActivity", "Google sign in failed")
                }
            } else {
                Log.w("SignInActivity", exception.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                mAuth.currentUser?.let { mUserViewModel.addUser(it.uid, it.email, it.displayName) }
                Log.d("SignInActivity", "signInWithCredential:success")
                val intent = Intent(this, ChoiceActivity::class.java)
                startActivity(intent)
            } else {
                Log.d("SignInActivity", "signInWithCredential:failure")
            }
        }

    }

    private fun checkAllFields(email: String, password: String): Boolean {

        if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.signInEmailTextView.error = null
        } else {
            binding.signInEmailTextView.error = "Check email format"
            return false
        }

        if (password.isNotEmpty()) {
            binding.signInPasswordTextView.error = null
        } else {
            binding.signInPasswordTextView.error = "This is required field"
            return false
        }

        return true
    }
}