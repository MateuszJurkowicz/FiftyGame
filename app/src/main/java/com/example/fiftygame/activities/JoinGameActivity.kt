package com.example.fiftygame.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.fiftygame.R
import com.example.fiftygame.data.models.Game
import com.example.fiftygame.data.viewmodels.GameViewModel
import com.example.fiftygame.databinding.ActivityJoinGameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JoinGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJoinGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentJoinGameContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentJoinGameContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()

    }
}
