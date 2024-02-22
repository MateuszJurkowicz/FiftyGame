package com.example.fiftygame.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.fiftygame.R

class CreateFieldsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_fields)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentFieldsContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

   override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentFieldsContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}