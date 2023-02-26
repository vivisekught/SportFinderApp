package com.example.sportfinderapp.presentation.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.sportfinderapp.domain.Training

class TrainingDiffUtilCallback: DiffUtil.ItemCallback<Training>() {
    override fun areItemsTheSame(oldItem: Training, newItem: Training): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Training, newItem: Training): Boolean {
        return oldItem == newItem
    }
}