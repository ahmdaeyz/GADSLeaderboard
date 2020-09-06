package dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard.learningleaders

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.ahmdaeyz.gadsleaderboard.data.model.Leader
import dev.ahmdaeyz.gadsleaderboard.data.network.service.NetworkService
import dev.ahmdaeyz.gadsleaderboard.util.LeadersResult
import dev.ahmdaeyz.gadsleaderboard.util.LeadersViewModel
import kotlinx.coroutines.Dispatchers

class LearningLeadersViewModel(private val networkService: NetworkService.GADS) :
    ViewModel(),
    LeadersViewModel {
    override val leaders: LiveData<LeadersResult<List<Leader>>> = liveData(Dispatchers.IO) {
        val leadersListResult = networkService.getHoursLeadersAsync().await()
        emit(leadersListResult)
    }
}
