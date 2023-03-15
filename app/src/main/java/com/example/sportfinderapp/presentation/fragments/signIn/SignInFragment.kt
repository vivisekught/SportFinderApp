package com.example.sportfinderapp.presentation.fragments.signIn

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
import com.example.sportfinderapp.databinding.FragmentSignInBinding
import com.example.sportfinderapp.presentation.SportAppFinderApp
import com.example.sportfinderapp.presentation.ViewModelFactory
import javax.inject.Inject


class SignInFragment : Fragment() {

    lateinit var viewModel: SignInViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentSignInBinding == null")

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
        _binding = FragmentSignInBinding.inflate(
            LayoutInflater.from(inflater.context), container, false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[SignInViewModel::class.java]
        setupOnClickListener()
        observeViewModel()
        addTextChangeListeners()
    }

    private fun setupOnClickListener() {
        with(binding) {
            goToRegistrationButton.setOnClickListener {
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
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
                is SignInState.EmptyEmail -> {
                    Log.d("Nikita", "EmptyEmail")
                    binding.emailTil.error = "empty email"
                }
                is SignInState.EmptyPassword -> {
                    binding.passwordTil.error = "empty password"
                }
                is SignInState.Loading -> {
                    binding.progressBarLoading.isVisible = true
                }

                is SignInState.IncorrectPassword -> {
                    binding.progressBarLoading.isVisible = false
                    binding.passwordTil.error = "Incorrect password!"
                }

                is SignInState.InvalidUserError -> {
                    binding.progressBarLoading.isVisible = false
                    binding.emailTil.error = "Email not found!"
                }
                is SignInState.NetworkError -> {
                    binding.progressBarLoading.isVisible = false
                    Toast.makeText(requireContext(), "Check Internet connection", Toast.LENGTH_LONG)
                        .show()
                }

                is SignInState.UnexpectedError -> {
                    binding.progressBarLoading.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is SignInState.Success -> {
                    binding.progressBarLoading.isVisible = false
                    findNavController().navigate(
                        SignInFragmentDirections.actionSignInFragmentToNavigationHome()
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
        fun newInstance() = SignInFragment()
    }
}