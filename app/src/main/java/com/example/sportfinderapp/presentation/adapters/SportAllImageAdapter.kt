package com.example.sportfinderapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportfinderapp.databinding.LayoutSportImageInAllBinding

class SportAllImageAdapter(private val images: IntArray) :
    RecyclerView.Adapter<SportAllImageAdapter.SportAllImageViewHolder>() {

    var setOnClickListener: ((IntArray, Int) -> Unit)? = null

    class SportAllImageViewHolder(val binding: LayoutSportImageInAllBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportAllImageViewHolder {
        val binding = LayoutSportImageInAllBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SportAllImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SportAllImageViewHolder, position: Int) {
        val image = images[position]
        holder.binding.layoutSportImageInAll.apply {
            setImageResource(image)
            setOnClickListener {
                setOnClickListener?.invoke(images, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}