package com.diegocalero.laliga.usecases

import com.diegocalero.laliga.data.repositories.TeamsRepository
import com.diegocalero.laliga.models.EventModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ListLastEventsByTeamIdTest {

    @Mock
    private lateinit var mockTeamsRepository: TeamsRepository

    private lateinit var listLastEventsByTeamId: ListLastEventsByTeamId

    @Before
    fun setUp() {
        listLastEventsByTeamId = ListLastEventsByTeamId(mockTeamsRepository)
    }

    @After
    fun finish() {
        Mockito.verifyNoMoreInteractions(mockTeamsRepository)
    }

    @Test
    fun invokeTest() {
        val teamId = "123"
        val events = listOf<EventModel>()

        Mockito.`when`(mockTeamsRepository.listLastEvents(teamId)).thenReturn(events)

        listLastEventsByTeamId.invoke(teamId)

        Mockito.verify(mockTeamsRepository).listLastEvents(teamId)
    }
}