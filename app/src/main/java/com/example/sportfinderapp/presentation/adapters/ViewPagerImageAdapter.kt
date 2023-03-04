package com.example.sportfinderapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportfinderapp.R

class ViewPagerImageAdapter(
    private val images: IntArray
): RecyclerView.Adapter<ViewPagerImageAdapter.ViewPagerViewHolder>() {
    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.layout_sport_fullscreen_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_sport_fullscreen_image,
            parent,
            false
        )
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val image = images[position]
        holder.imageView.setImageResource(image)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}