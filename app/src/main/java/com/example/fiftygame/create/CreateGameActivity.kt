package com.example.fiftygame.create

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.fiftygame.R
import com.example.fiftygame.SignInActivity

class CreateGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    fun backToSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

}