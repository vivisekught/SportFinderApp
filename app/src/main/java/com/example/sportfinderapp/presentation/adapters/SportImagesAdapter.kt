package com.example.sportfinderapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportfinderapp.databinding.LayoutSportImageBinding

class SportImagesAdapter(private val images: IntArray): RecyclerView.Adapter<SportImagesAdapter.SportImageViewHolder>() {

    var setOnClickListener: ((Int) -> Unit)? = null

    class SportImageViewHolder(val binding: LayoutSportImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportImageViewHolder {
        val binding = LayoutSportImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SportImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SportImageViewHolder, position: Int) {
        val image = images[position]
        holder.binding.layoutSportImage.apply {
            setImageResource(image)
            setOnClickListener {
                setOnClickListener?.invoke(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}