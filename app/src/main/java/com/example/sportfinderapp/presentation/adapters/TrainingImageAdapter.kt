package com.example.sportfinderapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportfinderapp.databinding.TrainingImageBinding

class TrainingImageAdapter(private val images: IntArray): RecyclerView.Adapter<TrainingImageAdapter.TrainingImageViewHolder>() {

    var setOnClickListener: ((IntArray, Int) -> Unit)? = null

    class TrainingImageViewHolder(val binding: TrainingImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingImageViewHolder {
        val binding = TrainingImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrainingImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainingImageViewHolder, position: Int) {
        val image = images[position]
        holder.binding.trainingImage.setImageResource(image)
        holder.binding.trainingImage.setOnClickListener {
            setOnClickListener?.invoke(images, position)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}