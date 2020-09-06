package dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard.skilliqleaders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.ahmdaeyz.gadsleaderboard.data.network.service.NetworkServiceImpl

class SkillIQLeadersViewModelFactory(
    private val networkService: NetworkServiceImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SkillIQLeadersViewModel(networkService) as T
    }
}