package com.diegocalero.laliga.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegocalero.laliga.models.TeamModel
import com.diegocalero.laliga.usecases.ListTeamsByLeagueName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel @ViewModelInject constructor(
    private val listTeamsByLeagueName: ListTeamsByLeagueName,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val teams = MutableLiveData<List<TeamModel>>()
    val showSnackbar: MutableLiveData<Boolean> = MutableLiveData(false)
    val snackBarMessage = MutableLiveData<String>()

    fun listTeams(leagueName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val teamsList = listTeamsByLeagueName.invoke(leagueName)
                teams.postValue(teamsList)
            } catch (exception: Exception) {
                exception.printStackTrace()
                showSnackbar.postValue(true)
                snackBarMessage.postValue(exception.message)
            }
        }
    }
}