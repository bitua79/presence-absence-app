package com.application.presence_absence.ui.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
        // button listener
        with(binding) {
            binding.btnLogin.setOnClickListener {
                val username = etUsername.text?.trim() ?: ""
                val password = etPassword.text?.trim() ?: ""
                if (password.isNotBlank() && username.isNotBlank()) {
                    viewModel.invokeSignInRequest(
                        PostLogin(
                            username = username.toString(),
                            password = password.toString()
                        )
                    )
                } else {
                    // TODO: handle validation
                }
            }
        }
    }

    private fun initControllers() {
        viewModel.uiViewState.collectOnFragment(this) {
            when (it) {
                is UiLoading -> {
                    // TODO: handle loading mode
                }

                is UiError -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                is UiSuccess -> {
                    goExamList()
                }

                else -> Unit
            }
        }
    }

    private fun goExamList() {
        val action = LoginFragmentDirections.actionLoginFragmentToExamListFragment()
        findNavController().navigate(action)
    }
}