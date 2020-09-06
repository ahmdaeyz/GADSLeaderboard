package dev.ahmdaeyz.gadsleaderboard.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainSharedViewModel : ViewModel() {

    private val _shouldNavigateToLeadersScreen = MutableLiveData<Boolean>()
    val shouldNavigateToLeadersScreen: LiveData<Boolean>
        get() = _shouldNavigateToLeadersScreen
    private val _shouldNavigateToSubmissionScreen = MutableLiveData<Boolean>()
    val shouldNavigateToSubmissionScreen: LiveData<Boolean>
        get() = _shouldNavigateToSubmissionScreen

    fun navigateToLeadersScreen() {
        _shouldNavigateToLeadersScreen.value = true
    }

    fun navigateToSubmissionScreen() {
        _shouldNavigateToSubmissionScreen.value = true
    }

}