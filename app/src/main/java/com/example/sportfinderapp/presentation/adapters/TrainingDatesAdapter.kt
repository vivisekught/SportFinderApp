package com.example.sportfinderapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportfinderapp.databinding.LayoutTrainingDateBinding
import com.example.sportfinderapp.domain.entity.Training

class TrainingDatesAdapter(private val trainings: List<List<Training>>) :
    RecyclerView.Adapter<TrainingDatesAdapter.TrainingDaysViewHolder>() {

    class TrainingDaysViewHolder(val binding: LayoutTrainingDateBinding) :
        RecyclerView.ViewHolder(binding.root)

    var setOnClickListener: ((Training) -> Unit)? = null

    var trainingAdapter: TrainingAdapter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingDaysViewHolder {
        val binding = LayoutTrainingDateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrainingDaysViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainingDaysViewHolder, position: Int) {
        val trainings = trainings[position]
        with(holder.binding) {
            trainingDateTv.text = trainings[0].date
            trainingAdapter = TrainingAdapter(trainings)
            trainingAdapter?.setOnClickListener = setOnClickListener
            trainingDateRv.adapter = trainingAdapter
        }
    }

    override fun getItemCount(): Int {
        return trainings.size
    }
}