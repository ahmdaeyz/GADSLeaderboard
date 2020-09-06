package dev.ahmdaeyz.gadsleaderboard.util

import androidx.lifecycle.LiveData
import dev.ahmdaeyz.gadsleaderboard.data.model.Leader

interface LeadersViewModel {
    val leaders: LiveData<LeadersResult<List<Leader>>>
}
