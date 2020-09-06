package dev.ahmdaeyz.gadsleaderboard.data.network.service

import dev.ahmdaeyz.gadsleaderboard.data.model.Leader
import dev.ahmdaeyz.gadsleaderboard.data.model.Score
import dev.ahmdaeyz.gadsleaderboard.data.model.ScoreType
import dev.ahmdaeyz.gadsleaderboard.data.network.service.apis.GADSAPI
import dev.ahmdaeyz.gadsleaderboard.data.network.service.apis.GADSAPIBuilder
import dev.ahmdaeyz.gadsleaderboard.data.network.service.apis.GoogleFormsAPI
import dev.ahmdaeyz.gadsleaderboard.data.network.service.apis.GoogleFormsAPIBuilder
import dev.ahmdaeyz.gadsleaderboard.util.LeadersResult
import dev.ahmdaeyz.gadsleaderboard.util.SubmissionResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class NetworkServiceImpl private constructor() :
    NetworkService.GADS, NetworkService.Submission {

    private val gadsApi: GADSAPI = GADSAPIBuilder()
    private val googleFormsAPI: GoogleFormsAPI = GoogleFormsAPIBuilder()

    companion object {
        private var INSTANCE: NetworkServiceImpl? = null

        fun getInstance(): NetworkServiceImpl {
            synchronized(this) {
                if (INSTANCE != null) {
                    throw Exception("You can't initialize the service, Already is.")
                } else {
                    INSTANCE = NetworkServiceImpl()
                    return INSTANCE!!
                }
            }
        }

    }


    override suspend fun getHoursLeadersAsync(): Deferred<LeadersResult<List<Leader>>> =
        CoroutineScope(Dispatchers.IO).async {
            val response = gadsApi.getHoursLeaders()
            response.body()?.let { hoursLeadersResponseList ->
                val hoursLeadersList =
                    hoursLeadersResponseList.map { hoursLeaderNetworkResponse ->
                        Leader(
                            hoursLeaderNetworkResponse.name,
                            Score(
                                hoursLeaderNetworkResponse.hours,
                                ScoreType.HOURS
                            ),
                            hoursLeaderNetworkResponse.country,
                            hoursLeaderNetworkResponse.badgeUrl
                        )
                    }
                return@async LeadersResult.Success(hoursLeadersList)
            }
            return@async LeadersResult.Error(response.errorBody().toString())
        }

    override suspend fun getSkillIQLeadersAsync(): Deferred<LeadersResult<List<Leader>>> =
        CoroutineScope(Dispatchers.IO).async {
            val response = gadsApi.getSkillIQLeaders()
            response.body()?.let { skillIQLeadersResponseList ->
                val skillIQLeadersList =
                    skillIQLeadersResponseList.map { skillIQLeaderNetworkResponse ->
                        Leader(
                            skillIQLeaderNetworkResponse.name,
                            Score(
                                skillIQLeaderNetworkResponse.score,
                                ScoreType.SKILL_IQ
                            ),
                            skillIQLeaderNetworkResponse.country,
                            skillIQLeaderNetworkResponse.badgeUrl
                        )
                    }
                return@async LeadersResult.Success(skillIQLeadersList)
            }
            return@async LeadersResult.Error(response.errorBody().toString())
        }

    override suspend fun submitProjectAsync(
        firstName: String,
        lastName: String,
        email: String,
        projectGithubUrl: String
    ): Deferred<SubmissionResult<Unit>> = CoroutineScope(Dispatchers.IO).async {
        val submission =
            googleFormsAPI.submitProjectToForm(firstName, lastName, email, projectGithubUrl)
        submission.errorBody()?.run {
            SubmissionResult.Error(submission.errorBody().toString())
        } ?: SubmissionResult.Success(Unit)
    }
}
