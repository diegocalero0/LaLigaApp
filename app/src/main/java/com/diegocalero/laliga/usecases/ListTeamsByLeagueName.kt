package com.diegocalero.laliga.usecases

import com.diegocalero.laliga.data.repositories.TeamsRepository
import com.diegocalero.laliga.models.TeamModel
import javax.inject.Inject

class ListTeamsByLeagueName @Inject constructor(private val teamsRepository: TeamsRepository) {
    fun invoke(leagueName: String): List<TeamModel> {
        return  teamsRepository.listTeams(leagueName)
    }
}