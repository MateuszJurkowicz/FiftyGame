package com.example.fiftygame.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.fiftygame.R

class CreateGamesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_games)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentGamesContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentGamesContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    /*fun editGame(game: Game) {
        val intent = Intent(this, CreateFieldsActivity::class.java)
        intent.putExtra("game ID", game.gameId)
        Log.d("create gameid", game.gameId.toString())
        startActivity(intent)
    }*/
}