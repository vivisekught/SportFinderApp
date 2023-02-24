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
                arrayListOf(Days.FRIDAY),
                Date(2505),
                "1",
                "2",
                "3",
                Level.BEGINNER,
                SportType.FOOTBALL,
                15224,
                "nikita"
            )
        )
        trainingList.add(
            Training(
                2,
                "2",
                "qwe",
                "qwe",
                arrayListOf(Days.FRIDAY),
                Date(2505),
                "1",
                "2",
                "3",
                Level.BEGINNER,
                SportType.FOOTBALL,
                15224,
                "nikita"
            )
        )
        trainingList.add(
            Training(
                3,
                "3",
                "qwe",
                "qwe",
                arrayListOf(Days.FRIDAY),
                Date(2505),
                "1",
                "2",
                "3",
                Level.BEGINNER,
                SportType.FOOTBALL,
                15224,
                "nikita"
            )
        )
        trainingList.add(
            Training(
                4,
                "4",
                "qwe",
                "qwe",
                arrayListOf(Days.FRIDAY),
                Date(2505),
                "1",
                "2",
                "3",
                Level.BEGINNER,
                SportType.FOOTBALL,
                15224,
                "nikita"
            )
        )
        trainingList.add(
            Training(
                5,
                "5",
                "qwe",
                "qwe",
                arrayListOf(Days.FRIDAY),
                Date(2505),
                "1",
                "2",
                "3",
                Level.BEGINNER,
                SportType.FOOTBALL,
                15224,
                "nikita"
            )
        )
        trainingList.add(
            Training(
                6,
                "6",
                "qwe",
                "qwe",
                arrayListOf(Days.FRIDAY),
                Date(2505),
                "1",
                "2",
                "3",
                Level.BEGINNER,
                SportType.FOOTBALL,
                15224,
                "nikita"
            )
        )
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