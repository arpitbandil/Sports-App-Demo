package com.arpitbandil.sportsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.arpitbandil.sportsapp.R
import com.arpitbandil.sportsapp.databinding.ActivityMainBinding
import com.arpitbandil.sportsapp.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navController = NavHostFragment.findNavController(
            supportFragmentManager.findFragmentById(R.id.home_nav_host) as NavHostFragment
        )
        binding.bottomMainNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomMainNav.onNavigationItemSelected(destination.id)
        }
    }
}