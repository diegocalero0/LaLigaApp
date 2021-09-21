package com.diegocalero.laliga.usecases

import com.diegocalero.laliga.data.repositories.TeamsRepository
import com.diegocalero.laliga.models.EventModel
import javax.inject.Inject

class ListLastEventsByTeamId @Inject constructor(private val teamsRepository: TeamsRepository) {
    fun invoke(teamId: String?): List<EventModel> {
        return teamsRepository.listLastEvents(teamId)
    }
}