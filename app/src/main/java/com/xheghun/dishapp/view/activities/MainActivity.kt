package com.xheghun.dishapp.view.activities

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.xheghun.dishapp.R
import com.xheghun.dishapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private  val TAG = "MainActivityLog"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_all_dishes, R.id.navigation_fav_dishes, R.id.navigation_random_dish
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        Log.d(TAG, "OnCreate called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "OnStart called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "OnRestart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "OnResume called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "OnStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "OnDestroy called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "OnPause called")
    }
}