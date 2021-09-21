package com.diegocalero.laliga.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegocalero.laliga.models.EventModel
import com.diegocalero.laliga.models.TeamModel
import com.diegocalero.laliga.usecases.ListLastEventsByTeamId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class TeamDetailViewModel @ViewModelInject constructor(
        private val listLastEventsByTeamId: ListLastEventsByTeamId,
        @Assisted private val savedStateHandle: SavedStateHandle): ViewModel() {

    val events = MutableLiveData<List<EventModel>>()
    val showSnackbar: MutableLiveData<Boolean> = MutableLiveData(false)
    val snackBarMessage = MutableLiveData<String>()
    val team: TeamModel? = savedStateHandle["team"]

    init {
        listEvents()
    }

    private fun listEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val eventsList = listLastEventsByTeamId.invoke(team?.idTeam)
                events.postValue(eventsList)
            } catch (exception: Exception) {
                exception.printStackTrace()
                showSnackbar.postValue(true)
                snackBarMessage.postValue(exception.message)
            }
        }
    }
}