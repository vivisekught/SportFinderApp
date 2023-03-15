package com.example.sportfinderapp.presentation.fragments.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sportfinderapp.databinding.FragmentLoginBinding
import com.example.sportfinderapp.presentation.SportAppFinderApp
import com.example.sportfinderapp.presentation.ViewModelFactory
import javax.inject.Inject


class LoginFragment : Fragment() {

    lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding == null")

    private val component by lazy {
        (requireActivity().application as SportAppFinderApp).component
    }


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(
            LayoutInflater.from(inflater.context), container, false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        setupOnClickListener()
        observeViewModel()
        addTextChangeListeners()
    }

    private fun setupOnClickListener() {
        with(binding) {
            goToRegistrationButton.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
            }
            loginButton.setOnClickListener {
                viewModel.loginCheck(
                    binding.emailEt.text.toString(),
                    binding.passwordEt.text.toString()
                )
            }
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is SingInState.EmptyEmail -> {
                    Log.d("Nikita", "EmptyEmail")
                    binding.emailTil.error = "empty email"
                }
                is SingInState.EmptyPassword -> {
                    binding.passwordTil.error = "empty password"
                }
//                is EmailNotFound -> {
//                    binding.emailTil.error = "email not found"
//                }
//                is IncorrectPassword -> {
//                    Log.d("Nikita", "IncorrectPassword")
//                    binding.passwordTil.error = "incorrect password"
//                }
                is SingInState.Loading -> {
                    binding.progressBarLoading.isVisible = true
                }
                is SingInState.Error -> {
                    binding.progressBarLoading.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is SingInState.Success -> {
                    binding.progressBarLoading.isVisible = false
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToNavigationHome()
                    )
                }
            }
        }
    }


    private fun addTextChangeListeners() {
        binding.emailEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.emailTil.error = null
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.passwordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.passwordTil.error = null
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }


    companion object {
        fun newInstance() = LoginFragment()
    }
}