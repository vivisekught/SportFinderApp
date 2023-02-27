package com.example.sportfinderapp.presentation.fragments.training

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.example.sportfinderapp.R
import com.example.sportfinderapp.domain.Training

class TrainingFragment : Fragment() {
    private val args by navArgs<TrainingFragmentArgs>()
    private val training: Training by lazy {
        args.training
    }

    private lateinit var updateToolbarTitle: UpdateToolbarTitle

    companion object {
        fun newInstance() = TrainingFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[TrainingViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is UpdateToolbarTitle) {
            updateToolbarTitle = context
        } else {
            throw RuntimeException("Activity must implement UpdateToolbarTitle")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateToolbarTitle.updateToolbarTitle(training.title)
        return inflater.inflate(R.layout.fragment_training, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val actionBar = requireActivity().actionBar

        Log.d("TrainingFragment", requireActivity().toString())

    }

    interface UpdateToolbarTitle {
        fun updateToolbarTitle(trainingTitle: String)
    }
}