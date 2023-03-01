package com.example.sportfinderapp.presentation.fragments.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sportfinderapp.R
import com.example.sportfinderapp.databinding.FragmentTrainingBinding
import com.example.sportfinderapp.domain.Training
import com.example.sportfinderapp.presentation.adapters.TrainingImageAdapter


class TrainingFragment : Fragment() {
    private val args by navArgs<TrainingFragmentArgs>()

    private lateinit var trainingImageAdapter: TrainingImageAdapter


    private var _binding: FragmentTrainingBinding? = null
    private val binding: FragmentTrainingBinding
        get() = _binding ?: throw RuntimeException("FragmentTrainingBinding == null")

    private lateinit var training: Training

    companion object {
        fun newInstance() = TrainingFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[TrainingViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        training = args.training
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrainingBinding.inflate(
            LayoutInflater.from(inflater.context),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView()

        binding.trainingPageViewAllTv.setOnClickListener {
            findNavController().navigate(
                TrainingFragmentDirections.actionTrainingFragmentToTrainingAllImagesFragment()
            )
        }
    }

    private fun setupRecyclerView() {
        with(binding.trainingPageImageRw) {
            trainingImageAdapter = TrainingImageAdapter()
            adapter = trainingImageAdapter
        }
        val training = R.drawable.box
        trainingImageAdapter.images =
            listOf(training, training, training, training, training, training)
        setupOnClickListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupOnClickListener() {
        trainingImageAdapter.setOnClickListener = {
            findNavController().navigate(
                TrainingFragmentDirections.actionTrainingFragmentToFullscreenTrainingImageFragment(
                    it
                )
            )
        }
    }


//    interface UpdateToolbarTitle {
//        fun updateToolbarTitle(trainingTitle: String)
//    }

//    override fun onResume() {
//        super.onResume()
//        val actionBar: ActionBar? = (activity as AppCompatActivity?)?.supportActionBar
//        if (actionBar != null) {
//            actionBar.title = training.title
//        }
//    }
}