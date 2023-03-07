package com.example.sportfinderapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportfinderapp.databinding.LayoutTrainingTimeBinding
import com.example.sportfinderapp.domain.entity.Training

class TrainingAdapter(private val trainings: List<Training>) :
    RecyclerView.Adapter<TrainingAdapter.TrainingTimesViewHolder>() {

    var setOnClickListener: ((Training) -> Unit)? = null

    class TrainingTimesViewHolder(val binding: LayoutTrainingTimeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingTimesViewHolder {
        val binding = LayoutTrainingTimeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrainingTimesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainingTimesViewHolder, position: Int) {
        val training = trainings[position]
        with(holder.binding) {
            trainingTime.text = training.time
            trainingLevel.text = training.level.toString()
            trainingAvailablePlaces.text = training.availablePlaces.toString()

            layoutTrainingCv.setOnClickListener {
                setOnClickListener?.invoke(training)
            }
        }

    }

    override fun getItemCount(): Int {
        return trainings.size
    }
}