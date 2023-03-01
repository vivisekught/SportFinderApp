package com.example.sportfinderapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.sportfinderapp.R
import com.example.sportfinderapp.databinding.FragmentFullscreenTrainingImageBinding
import kotlin.properties.Delegates

class FullscreenTrainingImageFragment : Fragment() {

    private val args by navArgs<FullscreenTrainingImageFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_fullscreen_training_image,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageId = args.imageId
        view.findViewById<ImageView>(R.id.training_fullscreen_image_iv).setImageResource(imageId)
    }
}