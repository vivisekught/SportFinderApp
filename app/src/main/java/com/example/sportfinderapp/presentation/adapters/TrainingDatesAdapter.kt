package com.example.sportfinderapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportfinderapp.R
import com.example.sportfinderapp.domain.entity.Training
//: RecyclerView.Adapter<TrainingDatesAdapter.TrainingDaysViewHolder>()
//class TrainingDatesAdapter(private val trainings: List<Training>)  {
//
//    var setOnClickListener: ((Training) -> Unit)? = null
//
//    class TrainingDaysViewHolder(val binding: TrainingDayBinding) :
//        RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingDaysViewHolder {
//        val binding = TrainingDayBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//        return TrainingDaysViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: TrainingDaysViewHolder, position: Int) {
//        val training = trainings[position]
//        with(holder.binding){
//            trainingDayDateTv.text = training.date
//            trainingDayLevelTv.text = training.level.toString()
//            trainingDayAvailablePlacesTv.text = String.format(training.availablePlaces.toString())
//            trainingDayCv.setOnClickListener {
//                setOnClickListener?.invoke(training)
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return trainings.size
//    }
//}