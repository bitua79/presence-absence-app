package com.application.presence_absence.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.application.presence_absence.R
import com.application.presence_absence.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val hasToolbarItems = setOf(
        R.id.examListFragment,
        R.id.studentListFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupNavController()
        setupNavigationUiState()
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

    }

    private fun setupNavigationUiState() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            with(binding.toolbar) {
                when (destination.id) {
                    in hasToolbarItems -> {
                        visibility = View.VISIBLE
                        title = destination.label
                    }

                    else -> {
                        visibility = View.GONE
                    }
                }
            }
        }
    }
}