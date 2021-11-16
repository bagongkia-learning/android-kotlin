package com.bca.learning.newsapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.bca.learning.newsapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController
    private val timerObject = object: CountDownTimer(30000, 3000) {
        override fun onTick(millisUntilFinished: Long) {
            Log.d("Count Down", millisUntilFinished.toString())
        }

        override fun onFinish() {
            val sharedPreferences = applicationContext?.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
            sharedPreferences?.edit()?.clear()
            navController.navigate(R.id.login_fragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onUserInteraction() {
        timerObject.cancel()
        timerObject.start()
        super.onUserInteraction()
    }
}