package com.utn.segundoparcial.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.utn.segundoparcial.R

class NavigationActivity : AppCompatActivity() {

    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var navHostFragment : NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_for_list) as NavHostFragment
        bottomNavView = findViewById(R.id.bottomBar)

        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)
    }
}