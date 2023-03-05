package com.example.sportfinderapp.presentation.fragments.training

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.sportfinderapp.R
import com.example.sportfinderapp.presentation.fragments.sport.SportPageFragmentArgs

class TrainingPageFragment : Fragment() {

    private val args by navArgs<TrainingPageFragmentArgs>()

    companion object {
        fun newInstance() = TrainingPageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_training_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Nikita",args.training.toString())
    }


}