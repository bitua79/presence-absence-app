package com.application.presence_absence.ui.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.application.presence_absence.R
import com.application.presence_absence.core.utils.FirebaseAnalyticsHelper
import com.application.presence_absence.databinding.FragmentLoginBinding
import com.application.presence_absence.domain.params.PostLogin
import com.application.presence_absence.ui.core.UiError
import com.application.presence_absence.ui.core.UiLoading
import com.application.presence_absence.ui.core.UiSuccess
import com.application.presence_absence.ui.core.UnAuthorizedError
import com.application.presence_absence.ui.utils.collectOnFragment
import com.application.presence_absence.ui.utils.createSnackbar
import com.application.presence_absence.ui.utils.gone
import com.application.presence_absence.ui.utils.hideKeyboard
import com.application.presence_absence.ui.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initControllers()
    }

    private fun initViews() {
        with(binding) {
            // Remove Edit text error by text changes
            with(etUsername) {
                doAfterTextChanged {
                    if (!text.isNullOrBlank()) {
                        tilUsername.error = null
                        tilUsername.isErrorEnabled = false
                    }
                }
            }
            with(etPassword) {
                doAfterTextChanged {
                    if (!text.isNullOrBlank()) {
                        tilPassword.error = null
                        tilPassword.isErrorEnabled = false
                    }
                }
            }

            // Handle Login request by button
            btnLogin.setOnClickListener {
                hideKeyboard()

                // Log login button select content
                FirebaseAnalyticsHelper().logLoginSelectContent()

                // Send Auth information to API
                val username = etUsername.text?.trim()
                val password = etPassword.text?.trim()
                if (validate(username, password))
                    sendLoginRequest(username.toString(), password.toString())
            }
        }
    }

    private fun validate(username: CharSequence?, password: CharSequence?): Boolean {
        var valid = true
        if (username.isNullOrBlank()) {
            binding.tilUsername.error = getString(R.string.msg_required)
            binding.etUsername.requestFocus()
            valid = false
        }
        if (password.isNullOrBlank()) {
            binding.tilPassword.error = getString(R.string.msg_required)
            binding.etPassword.requestFocus()
            valid = false
        } else if (password.length < 8) {
            binding.tilPassword.error = getString(R.string.msg_password_at_least_8_digit)
            binding.etPassword.requestFocus()
            valid = false
        }
        return valid
    }

    private fun sendLoginRequest(username: String, password: String) {
        viewModel.invokeLoginRequest(
            PostLogin(
                username = username, password = password
            )
        )
    }

    private fun initControllers() {
        viewModel.uiViewState.collectOnFragment(this) {
            if (it is UiLoading) {
                binding.btnLogin.gone()
                binding.pbLoading.visible()
            } else {
                binding.pbLoading.gone()
                binding.btnLogin.visible()
            }

            if (it is UiSuccess) {
                goExamList()
                viewModel.clearState()
            }
            if (it is UiError) {
                if (it is UnAuthorizedError) {
                    createSnackbar(
                        R.string.msg_wrong_user_password,
                        binding.dividerSnackBarView
                    ).show()
                } else {
                    createSnackbar(it.errorStringId, binding.dividerSnackBarView).show()
                }
                viewModel.clearState()
            }
        }
    }

    private fun goExamList() {
        val action = LoginFragmentDirections.actionLoginFragmentToExamListFragment()
        findNavController().navigate(action)
    }
}