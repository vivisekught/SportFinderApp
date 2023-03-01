package com.example.sportfinderapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportfinderapp.databinding.TrainingInAllImageBinding

class TrainingAllImageAdapter(private val images: IntArray) : RecyclerView.Adapter<TrainingAllImageAdapter.TrainingAllImageViewHolder>() {

    var setOnClickListener: ((IntArray, Int) -> Unit)? = null

    class TrainingAllImageViewHolder(val binding: TrainingInAllImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingAllImageViewHolder {
        val binding = TrainingInAllImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrainingAllImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainingAllImageViewHolder, position: Int) {
        val image = images[position]
        holder.binding.trainingImageInAll.setImageResource(image)
        holder.binding.trainingImageInAll.setOnClickListener {
            setOnClickListener?.invoke(images, position)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}