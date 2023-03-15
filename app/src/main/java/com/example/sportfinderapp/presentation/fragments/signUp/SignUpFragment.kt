package com.example.sportfinderapp.presentation.fragments.signUp

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sportfinderapp.databinding.FragmentSignUpBinding
import com.example.sportfinderapp.presentation.SportAppFinderApp
import com.example.sportfinderapp.presentation.ViewModelFactory
import javax.inject.Inject

class SignUpFragment : Fragment() {

    lateinit var viewModel: SignUpViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding
        get() = _binding ?: throw RuntimeException("FragmentSignUpBinding == null")

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
        _binding = FragmentSignUpBinding.inflate(
            LayoutInflater.from(inflater.context), container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[SignUpViewModel::class.java]
        setupOnClickListener()
        observeViewModel()
        addTextChangeListeners()

    }

    private fun addTextChangeListeners() {
        binding.fullNameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.fullNameTil.error = null
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
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

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                SignUpState.EmailAlreadyUsedError -> {
                    binding.progressBarLoading.isVisible = false
                    binding.emailTil.error = "Email already used"
                }
                SignUpState.EmptyFullName -> {
                    binding.fullNameTil.error = "Empty full name"
                }
                SignUpState.IncorrectEmail -> {
                    binding.emailTil.error = "Incorrect email"
                }
                SignUpState.Loading -> {
                    binding.progressBarLoading.isVisible = true
                }
                SignUpState.NetworkError -> {
                    binding.progressBarLoading.isVisible = false
                    Toast.makeText(requireContext(), "Check Internet connection", Toast.LENGTH_LONG)
                        .show()
                }
                is SignUpState.UnexpectedError -> {
                    binding.progressBarLoading.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is SignUpState.WeakPasswordError -> {
                    binding.progressBarLoading.isVisible = false
                    binding.passwordTil.error = it.reason
                }
                SignUpState.Success -> {
                    binding.progressBarLoading.isVisible = false
                    findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToNavigationHome())
                }
                SignUpState.EmptyPassword -> {
                    binding.passwordTil.error = "Empty password"
                }
            }
        }
    }

    private fun setupOnClickListener() {
        with(binding) {
            joinButton.setOnClickListener {
                viewModel.registration(
                    binding.fullNameEt.text.toString(),
                    binding.emailEt.text.toString(),
                    binding.passwordEt.text.toString()
                )
            }

            alreadyHaveAccButton.setOnClickListener {
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
            }
        }
    }
}