package com.diegocalero.laliga.repositories

import com.diegocalero.laliga.data.datasources.TeamsRemoteDataSource
import com.diegocalero.laliga.data.repositories.TeamsRepository
import com.diegocalero.laliga.data.repositories.TeamsRepositoryImpl
import com.diegocalero.laliga.models.EventModel
import com.diegocalero.laliga.models.TeamModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TeamsRepositoryTest {

    @Mock
    private lateinit var mockTeamsRemoteDataSource: TeamsRemoteDataSource

    private lateinit var teamsRepository: TeamsRepository

    @Before
    fun setUp() {
        teamsRepository = TeamsRepositoryImpl(mockTeamsRemoteDataSource)
    }

    @After
    fun finish() {
        Mockito.verifyNoMoreInteractions(mockTeamsRemoteDataSource)
    }

    @Test
    fun listTeamsTest() {
        val leagueName = "Spanish La Liga"
        val teams = listOf<TeamModel>()

        Mockito.`when`(mockTeamsRemoteDataSource.listTeams(leagueName)).thenReturn(teams)

        teamsRepository.listTeams(leagueName)
        Mockito.verify(mockTeamsRemoteDataSource).listTeams(leagueName)
    }

    fun listEvents() {

        val teamId = "123"
        val events = listOf<EventModel>()

        Mockito.`when`(mockTeamsRemoteDataSource.listLastEvents(teamId)).thenReturn(events)

        teamsRepository.listLastEvents(teamId)

        Mockito.verify(mockTeamsRemoteDataSource).listLastEvents(teamId)
    }
}