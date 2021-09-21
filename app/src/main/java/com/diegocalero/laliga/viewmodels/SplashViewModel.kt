package com.diegocalero.laliga.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {
    val splashFinished: MutableLiveData<Boolean> = MutableLiveData(false)

    fun initSplash() {
        viewModelScope.launch {
            delay(2000)
            splashFinished.postValue(true)
        }
    }
}