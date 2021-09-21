package com.diegocalero.laliga.data.datasources

import com.diegocalero.laliga.BuildConfig
import com.diegocalero.laliga.apis.TheSportDBApi
import com.diegocalero.laliga.data.dtos.toEventModel
import com.diegocalero.laliga.data.dtos.toTeamModel
import com.diegocalero.laliga.models.EventModel
import com.diegocalero.laliga.models.TeamModel
import java.lang.Exception
import javax.inject.Inject

interface TeamsRemoteDataSource {
    fun listTeams(leagueName: String): List<TeamModel>
    fun listLastEvents(teamId: String?): List<EventModel>
}

class TeamsRemoteDataSourceImpl @Inject constructor (private val theSportDBApi: TheSportDBApi): TeamsRemoteDataSource {

    override fun listTeams(leagueName: String): List<TeamModel> {
        val response = theSportDBApi.listAllTeams(
            apiKey = BuildConfig.THE_SPORT_DB_API_KEY,
            leagueName = leagueName
        ).execute()


        if(response.isSuccessful) {
            val body = response.body()

            if(body != null) {
                return body.teams.map {
                    it.toTeamModel()
                }
            } else {
                throw Exception("Unknown error")
            }
        } else {
            throw Exception(response.message())
        }
    }

    override fun listLastEvents(teamId: String?): List<EventModel> {
        val response = theSportDBApi.listLastEvents(
            apiKey = BuildConfig.THE_SPORT_DB_API_KEY,
            teamId = teamId
        ).execute()


        if(response.isSuccessful) {
            val body = response.body()

            if(body != null) {
                return body.results.map {
                    it.toEventModel()
                }
            } else {
                throw Exception("Unknown error")
            }
        } else {
            throw Exception(response.message())
        }
    }
}