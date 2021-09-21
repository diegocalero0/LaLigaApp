package com.diegocalero.laliga.usecases

import com.diegocalero.laliga.data.repositories.TeamsRepository
import com.diegocalero.laliga.models.TeamModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListTeamsByLeagueNameTest {

    @Mock
    private lateinit var mockTeamsRepository: TeamsRepository

    private lateinit var listTeamsByLeagueName: ListTeamsByLeagueName

    @Before
    fun setUp() {
        listTeamsByLeagueName = ListTeamsByLeagueName(mockTeamsRepository)
    }

    @After
    fun finish() {
        Mockito.verifyNoMoreInteractions(mockTeamsRepository)
    }

    @Test
    fun invokeTest() {
        val leagueName = "Name Test"
        val teams = listOf<TeamModel>()

        Mockito.`when`(mockTeamsRepository.listTeams(leagueName)).thenReturn(teams)

        listTeamsByLeagueName.invoke(leagueName)

        Mockito.verify(mockTeamsRepository).listTeams(leagueName)
    }
}