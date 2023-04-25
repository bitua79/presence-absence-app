package com.application.presence_absence.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.application.presence_absence.R
import com.application.presence_absence.util.runOnMain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val DELAY = 3000L
@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        job = lifecycleScope.launch(Dispatchers.IO) {
            delay(DELAY)
            runOnMain { navigate() }
            job = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job?.cancel()
        job = null
    }

    private fun navigate() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
    }
}