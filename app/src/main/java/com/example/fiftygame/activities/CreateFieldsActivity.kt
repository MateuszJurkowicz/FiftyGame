package com.example.fiftygame.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.fiftygame.R
import com.example.fiftygame.fragments.create_fields.ListFieldsFragment

class CreateFieldsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_fields)

        Log.d("fields", "CreateFieldsActivity")


        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = ListFieldsFragment()

        val intent = intent
        val gameId = intent.getIntExtra("game ID", 1)

        val mBundle = Bundle()
        mBundle.putInt("game ID", gameId)
        mFragment.arguments = mBundle
        mFragmentTransaction.add(R.id.fields_ConstraintLayout, mFragment).commit()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentFieldsContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentFieldsContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    fun backToSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

}