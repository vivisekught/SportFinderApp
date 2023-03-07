package com.example.sportfinderapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sportfinderapp.R
import com.example.sportfinderapp.databinding.LayoutSportBinding
import com.example.sportfinderapp.domain.entity.Sport
import com.example.sportfinderapp.presentation.diffUtil.SportDiffUtilCallback

class UserSportsAdapter : ListAdapter<Sport, UserSportsAdapter.SportViewHolder>(
    SportDiffUtilCallback()
) {

    private lateinit var context: Context

    var setOnMoreClickListener: ((View, Int) -> Unit)? = null
    var setOnClickListener: ((Sport) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        context = parent.context
        val binding = LayoutSportBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return SportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        val sport = getItem(position)
        val binding = holder.binding
        with(binding) {
            sportTitleTv.text = sport.title
            sportCoachNameTv.text = sport.coachName
            sportImg.setImageResource(R.drawable.box)
            sportCardView.setOnClickListener {
                setOnClickListener?.invoke(sport)
            }

            sportMenuButton.setOnClickListener{
                setOnMoreClickListener?.invoke(it, sport.id)
            }
        }
    }

    class SportViewHolder(
        val binding: LayoutSportBinding
    ) : RecyclerView.ViewHolder(binding.root)
}