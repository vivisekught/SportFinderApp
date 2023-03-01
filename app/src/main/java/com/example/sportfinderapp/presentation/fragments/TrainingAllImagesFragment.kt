package com.example.sportfinderapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sportfinderapp.R
import com.example.sportfinderapp.presentation.adapters.TrainingAllImageAdapter
import com.example.sportfinderapp.presentation.adapters.TrainingImageAdapter


class TrainingAllImagesFragment : Fragment() {

    private lateinit var trainingAllImageAdapter: TrainingAllImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training_all_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.training_page_all_images_rw)
        trainingAllImageAdapter = TrainingAllImageAdapter()
        recyclerView.adapter = trainingAllImageAdapter
        val training = R.drawable.box
        trainingAllImageAdapter.images =
            listOf(training, training, training, training, training, training, training)

        trainingAllImageAdapter.setOnClickListener = {
            findNavController().navigate(
                TrainingAllImagesFragmentDirections
                    .actionTrainingAllImagesFragmentToFullscreenTrainingImageFragment(
                    it
                )
            )
        }
    }
}