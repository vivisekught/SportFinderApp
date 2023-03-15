package com.example.sportfinderapp.presentation.fragments.registration

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
import com.example.sportfinderapp.databinding.FragmentRegistrationBinding
import com.example.sportfinderapp.domain.entity.Response
import com.example.sportfinderapp.presentation.SportAppFinderApp
import com.example.sportfinderapp.presentation.ViewModelFactory
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    lateinit var viewModel: RegistrationViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() = _binding ?: throw RuntimeException("FragmentRegistrationBinding == null")

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
        _binding = FragmentRegistrationBinding.inflate(
            LayoutInflater.from(inflater.context), container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[RegistrationViewModel::class.java]
        setupOnClickListener()
        observeViewModel()
        addTextChangeListeners()

    }

    private fun addTextChangeListeners() {
        binding.fullNameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputFullName()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.emailEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputEmail()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.passwordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputPassword()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun observeViewModel() {
        with(viewModel) {
            errorInputFullName.observe(viewLifecycleOwner) {
                val message = if (it) {
                    "Empty name"
                } else {
                    null
                }
                binding.fullNameTil.error = message
            }
            errorInputEmail.observe(viewLifecycleOwner) {
                val message = if (it) {
                    "Incorrect email"
                } else {
                    null
                }
                binding.emailTil.error = message
            }

            errorInputPassword.observe(viewLifecycleOwner) {
                val message = if (it) {
                    "Incorrect password"
                } else {
                    null
                }
                binding.passwordTil.error = message
            }
            errorMessage.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }

            singUpState.observe(viewLifecycleOwner) {
                when (it) {
                    is Response.Loading -> {
                        binding.progressBarLoading.isVisible = true
                    }
                    is Response.Error -> {
                        binding.progressBarLoading.isVisible = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    is Response.Success -> {
                        binding.progressBarLoading.isVisible = false
                        findNavController().navigate(
                            RegistrationFragmentDirections.actionRegistrationFragmentToNavigationHome()
                        )
                    }
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
                findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
            }
        }
    }
}