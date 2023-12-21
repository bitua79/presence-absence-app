package com.application.presence_absence.ui.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.application.presence_absence.R
import com.application.presence_absence.core.extensions.collectOnFragment
import com.application.presence_absence.databinding.FragmentLoginBinding
import com.application.presence_absence.domain.params.PostLogin
import com.application.presence_absence.ui.widgets.UiError
import com.application.presence_absence.ui.widgets.UiLoading
import com.application.presence_absence.ui.widgets.UiSuccess
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
                addTextChangedListener {
                    if (!text.isNullOrBlank()) {
                        tilUsername.error = null
                        tilUsername.isErrorEnabled = false
                    }
                }
            }
            with(etPassword) {
                addTextChangedListener {
                    if (!text.isNullOrBlank()) {
                        tilPassword.error = null
                        tilPassword.isErrorEnabled = false
                    }
                }
            }

            // Handle Login request by button
            btnLogin.setOnClickListener {
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
                binding.btnLogin.visibility = View.INVISIBLE
                binding.pbLoading.visibility = View.VISIBLE
            } else {
                binding.pbLoading.visibility = View.GONE
                binding.btnLogin.visibility = View.VISIBLE
            }

            if (it is UiSuccess) {
                goExamList()
            }
            if (it is UiError) {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun goExamList() {
        val action = LoginFragmentDirections.actionLoginFragmentToExamListFragment()
        findNavController().navigate(action)
    }
}