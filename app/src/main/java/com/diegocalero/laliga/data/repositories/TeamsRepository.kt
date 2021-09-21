package com.diegocalero.laliga.data.repositories

import com.diegocalero.laliga.data.datasources.TeamsRemoteDataSource
import com.diegocalero.laliga.models.EventModel
import com.diegocalero.laliga.models.TeamModel
import javax.inject.Inject

interface TeamsRepository {
    fun listTeams(leagueName: String): List<TeamModel>
    fun listLastEvents(teamId: String?): List<EventModel>
}

class TeamsRepositoryImpl @Inject constructor (private val teamsRemoteDataSource: TeamsRemoteDataSource): TeamsRepository {
    override fun listTeams(leagueName: String): List<TeamModel> {
        return teamsRemoteDataSource.listTeams(leagueName)
    }

    override fun listLastEvents(teamId: String?): List<EventModel> {
        return teamsRemoteDataSource.listLastEvents(teamId)
    }
}