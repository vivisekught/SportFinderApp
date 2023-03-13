package com.example.sportfinderapp.presentation.fragments.registration

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sportfinderapp.databinding.FragmentRegistrationBinding
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

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            finishRegistration.observe(viewLifecycleOwner) {
                findNavController().navigate(
                    RegistrationFragmentDirections.actionRegistrationFragmentToNavigationHome(
                        viewModel.user?.value ?: throw RuntimeException("User doesnt create")
                    )
                )
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
            showPasswordCb.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    passwordEt.transformationMethod = HideReturnsTransformationMethod.getInstance()
                } else {
                    passwordEt.transformationMethod = PasswordTransformationMethod.getInstance()
                }
            }

            alreadyHaveAccButton.setOnClickListener {
                findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
            }
        }

    }
}