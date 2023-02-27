package com.example.sportfinderapp.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sportfinderapp.R
import com.example.sportfinderapp.databinding.TrainingBinding
import com.example.sportfinderapp.domain.Training
import com.example.sportfinderapp.presentation.diffUtil.TrainingDiffUtilCallback
import com.example.sportfinderapp.util.Days
import kotlin.reflect.full.memberProperties

class TrainingAdapter : ListAdapter<Training, TrainingAdapter.TrainingViewHolder>(
    TrainingDiffUtilCallback()
) {

    private lateinit var context: Context

    var setOnMoreClickListener: ((View, Int) -> Unit)? = null
    var setOnClickListener: ((Training) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        context = parent.context
        val binding = TrainingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TrainingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val training = getItem(position)
        val binding = holder.binding
        with(binding) {
            trainingTitleTv.text = training.title
            trainingCoachNameTv.text = training.coachName
            trainingImg.setImageResource(R.drawable.box)
            trainingCardView.setOnClickListener {
                setOnClickListener?.invoke(training)
            }
            trainingMenuButton.setOnClickListener {
                Log.d("TrainingAdapter", "more")
            }
            trainingDatesTv.text = formatTrainingDays(training)

            trainingMenuButton.setOnClickListener{
                setOnMoreClickListener?.invoke(it, training.id)
            }
        }
    }

    private fun formatTrainingDays(training: Training): String {
        val days = mapOf(
            "monday" to 0 ,
            "tuesday" to 1,
            "wednesday" to 2,
            "thursday" to 3,
            "friday" to 4,
            "saturday" to 5,
            "sunday" to 6
        )
        val daysFromResources: Array<String> = context.resources.getStringArray(R.array.days)
        var result = ""
        for (prop in Training::class.memberProperties) {
            if (prop.name in days.keys && prop.get(training) != null) {
                val day = days[prop.name] ?: throw RuntimeException("day - ${prop.name} no in days map")
                result += "${daysFromResources[day]} - ${prop.get(training)}\n"
            }
        }
        return result
    }

    class TrainingViewHolder(
        val binding: TrainingBinding
    ) : RecyclerView.ViewHolder(binding.root)
}