package com.application.presence_absence.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.presence_absence.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

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
    }

    private fun initViews() {


        // button listener
        binding.btnLogin.setOnClickListener {
            goExamList()
        }
    }

    private fun checkInput() {
        with(binding) {

            val username = etUsername.text?.trim() ?: ""
            val pass = etPassword.text?.trim() ?: ""

            // TODO: send username/pass to server and verify user
        }
    }

    private fun goExamList() {
        val action = LoginFragmentDirections.actionLoginFragmentToExamListFragment()
        findNavController().navigate(action)
    }
}