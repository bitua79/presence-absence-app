package com.application.presence_absence.ui.features.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.application.presence_absence.R
import com.application.presence_absence.core.extensions.runOnMain
import com.application.presence_absence.ui.utils.DetectionUtils.isEmulator
import com.application.presence_absence.ui.utils.DetectionUtils.isRooted
import com.application.presence_absence.ui.utils.createSnackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


const val DELAY = 3000L

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isRooted(requireContext()) || isEmulator(requireContext())) {
            createSnackbar(R.string.msg_non_security, R.string.label_exit).show()
        } else {
            job = lifecycleScope.launch(Dispatchers.IO) {
                delay(DELAY)
                runOnMain { navigate() }
                job = null
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job?.cancel()
        job = null
    }

    private fun navigate() {
        val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
        findNavController().navigate(action)
    }
}