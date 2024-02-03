package com.application.presence_absence.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.application.presence_absence.R
import com.application.presence_absence.databinding.ActivityMainBinding
import com.application.presence_absence.ui.utils.createSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupNavController()
        handleBackPressed()
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private var doubleBackToExitPressedOnce = false
    private fun handleBackPressed() {

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackToExitPressedOnce || navController.currentDestination?.id != R.id.loginFragment) {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                } else {
                    doubleBackToExitPressedOnce = true
                    createSnackbar(R.string.msg_press_again_to_exit, binding.root).show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        doubleBackToExitPressedOnce = false
                    }, 2000)
                }
                isEnabled = true
            }
        })
    }
}