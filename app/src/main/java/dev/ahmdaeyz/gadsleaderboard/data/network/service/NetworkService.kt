package dev.ahmdaeyz.gadsleaderboard.data.network.service

import dev.ahmdaeyz.gadsleaderboard.data.model.Leader
import dev.ahmdaeyz.gadsleaderboard.util.LeadersResult
import dev.ahmdaeyz.gadsleaderboard.util.SubmissionResult
import kotlinx.coroutines.Deferred

interface NetworkService {
    interface GADS {
        suspend fun getHoursLeadersAsync(): Deferred<LeadersResult<List<Leader>>>
        suspend fun getSkillIQLeadersAsync(): Deferred<LeadersResult<List<Leader>>>
    }

    interface Submission {
        suspend fun submitProjectAsync(
            firstName: String,
            lastName: String,
            email: String,
            projectGithubUrl: String
        ): Deferred<SubmissionResult<Unit>>
    }
}
