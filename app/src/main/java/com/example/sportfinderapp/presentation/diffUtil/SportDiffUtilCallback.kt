package com.example.sportfinderapp.presentation.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.sportfinderapp.domain.entity.Sport

class SportDiffUtilCallback: DiffUtil.ItemCallback<Sport>() {
    override fun areItemsTheSame(oldItem: Sport, newItem: Sport): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Sport, newItem: Sport): Boolean {
        return oldItem == newItem
    }
}