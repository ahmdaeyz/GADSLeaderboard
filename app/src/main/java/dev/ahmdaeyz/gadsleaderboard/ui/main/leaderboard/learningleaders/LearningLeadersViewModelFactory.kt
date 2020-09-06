package dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard.learningleaders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.ahmdaeyz.gadsleaderboard.data.network.service.NetworkServiceImpl

class LearningLeadersViewModelFactory(
    private val networkService: NetworkServiceImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LearningLeadersViewModel(networkService) as T
    }
}