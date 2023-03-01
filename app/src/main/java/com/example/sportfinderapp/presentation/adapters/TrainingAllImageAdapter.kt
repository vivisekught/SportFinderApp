package com.example.sportfinderapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportfinderapp.databinding.TrainingAllImageBinding
import com.example.sportfinderapp.databinding.TrainingImageBinding

class TrainingAllImageAdapter : RecyclerView.Adapter<TrainingAllImageAdapter.TrainingAllImageViewHolder>() {

    var images = listOf<Int>()

    var setOnClickListener: ((Int) -> Unit)? = null

    class TrainingAllImageViewHolder(val binding: TrainingAllImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingAllImageViewHolder {
        val binding = TrainingAllImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrainingAllImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainingAllImageViewHolder, position: Int) {
        val image = images[position]
        holder.binding.trainingAllImage.setImageResource(image)
        holder.binding.trainingAllImage.setOnClickListener {
            setOnClickListener?.invoke(image)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}