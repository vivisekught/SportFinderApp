package com.example.sportfinderapp.presentation.fragments.resetPassword

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
import com.example.sportfinderapp.databinding.FragmentResetPasswordBinding
import com.example.sportfinderapp.presentation.SportAppFinderApp
import com.example.sportfinderapp.presentation.ViewModelFactory
import javax.inject.Inject

class ResetPasswordFragment : Fragment() {

    lateinit var viewModel: ResetPasswordViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentResetPasswordBinding? = null
    private val binding: FragmentResetPasswordBinding
        get() = _binding ?: throw RuntimeException("FragmentResetPasswordBinding == null")

    private val component by lazy {
        (requireActivity().application as SportAppFinderApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResetPasswordBinding.inflate(
            LayoutInflater.from(inflater.context), container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[ResetPasswordViewModel::class.java]
        setupOnClickListener()
        observeViewModel()
        addTextChangeListeners()
    }

    private fun setupOnClickListener() {
        with(binding) {
            resetPasswordButton.setOnClickListener {
                viewModel.resetPasswordCheck(binding.emailEt.text.toString())
            }
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is ResetPasswordState.EmptyEmail -> {
                    binding.emailTil.error = "Empty email"
                }
                is ResetPasswordState.Loading -> {
                    binding.progressBarLoading.isVisible = true
                }
                ResetPasswordState.EmailNotFound -> {
                    binding.progressBarLoading.isVisible = false
                    binding.emailTil.error = "Email not found"
                }
                ResetPasswordState.IncorrectEmailError -> {
                    binding.progressBarLoading.isVisible = false
                    binding.emailTil.error = "Incorrect email"
                }
                ResetPasswordState.NetworkError -> {
                    binding.progressBarLoading.isVisible = false
                    Toast.makeText(requireContext(), "Check Internet connection", Toast.LENGTH_LONG)
                        .show()
                }
                ResetPasswordState.Success -> {
                    binding.progressBarLoading.isVisible = false
                    Toast.makeText(requireContext(), "Check your email!", Toast.LENGTH_LONG)
                        .show()
                    findNavController().navigate(ResetPasswordFragmentDirections.actionResetPasswordFragmentToSignInFragment())
                }
                is ResetPasswordState.UnexpectedError -> {
                    binding.progressBarLoading.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}