package com.example.sportfinderapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sportfinderapp.domain.Training
import com.example.sportfinderapp.domain.TrainingRepository
import com.example.sportfinderapp.util.Days
import com.example.sportfinderapp.util.Level
import com.example.sportfinderapp.util.SportType
import java.util.*


class TrainingRepositoryImpl: TrainingRepository {

    private val trainingListLD = MutableLiveData<List<Training>>()
    private val  trainingList = sortedSetOf<Training>({ o1, o2 -> o1.id.compareTo(o2.id) })

    init {
        trainingList.add(
            Training(
                1,
                "1",
                "qwe",
                "qwe",
                "13:00",
                                "13:00",
                null,
                null,
                null,
                null,
                null,
                "2",
                Level.BEGINNER,
                SportType.FOOTBALL,
                15224,
                "nikita"
            )
        )
        trainingList.add(Training(
            2,
            "1",
            "qwe",
            "qwe",
            "13:00",
            "13:00",
            null,
            null,
            null,
            null,
            null,
            "2",
            Level.BEGINNER,
            SportType.FOOTBALL,
            15224,
            "nikita"
        ))
        updateList()
    }

    override fun addTrainingUseCase(training: Training) {
        TODO("Not yet implemented")
    }

    override fun getTrainingUseCase(id: String): Training? {
        TODO("Not yet implemented")
    }

    override fun removeTrainingUseCase(id: String) {
        TODO("Not yet implemented")
    }

    override fun getTrainingList(): LiveData<List<Training>> {
        return trainingListLD
    }

    private fun updateList() {
        trainingListLD.value = trainingList.toList()
    }
}