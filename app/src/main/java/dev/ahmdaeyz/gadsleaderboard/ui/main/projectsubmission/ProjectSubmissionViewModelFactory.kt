package dev.ahmdaeyz.gadsleaderboard.ui.main.projectsubmission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.ahmdaeyz.gadsleaderboard.data.network.service.NetworkServiceImpl

class ProjectSubmissionViewModelFactory(
    private val networkService: NetworkServiceImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProjectSubmissionViewModel(networkService) as T
    }
}
