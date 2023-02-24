package com.example.sportfinderapp.presentation.fragments.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sportfinderapp.R
import com.example.sportfinderapp.databinding.FragmentHomeBinding
import com.example.sportfinderapp.presentation.adapters.TrainingAdapter

class HomeFragment : Fragment() {

    private lateinit var trainingAdapter: TrainingAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
            LayoutInflater.from(inflater.context),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.trainingList.observe(viewLifecycleOwner) {
            trainingAdapter.submitList(it)
        }

    }

    private fun setupRecyclerView() {
        with(binding.trainingRv) {
            trainingAdapter = TrainingAdapter()
            adapter = trainingAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}