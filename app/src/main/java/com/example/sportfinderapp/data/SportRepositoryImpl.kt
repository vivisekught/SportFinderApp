package com.example.sportfinderapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sportfinderapp.domain.entity.Sport
import com.example.sportfinderapp.domain.repository.SportRepository
import com.example.sportfinderapp.util.Level
import com.example.sportfinderapp.util.SportType
import javax.inject.Inject


class SportRepositoryImpl @Inject constructor(): SportRepository {

    private val sportListLD = MutableLiveData<List<Sport>>()
    private val sportList = sortedSetOf<Sport>({ o1, o2 -> o1.id.compareTo(o2.id) })

    init {
        sportList.add(
            Sport(
                1,
                "1",
                "wer",
                "qwe",
                "1",
                SportType.FOOTBALL,
                Level.DIFFERENT,
                15224,
                "nikita"
            )
        )
        sportList.add(
            Sport(
                2,
                "2",
                "rty",
                "tyu",
                "2",
                SportType.BASKETBALL,
                Level.EXPERT,
                1,
                "olena"
            )
        )
        updateList()
    }



    private fun updateList() {
        sportListLD.value = sportList.toList()
    }

    override fun addSportUseCase(sport: Sport) {
        TODO("Not yet implemented")
    }

    override fun getSportUseCase(id: Int): Sport? {
        TODO("Not yet implemented")
    }

    override fun removeSportUseCase(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getSportList(coachId: Int): LiveData<List<Sport>> {
        return sportListLD
    }
}