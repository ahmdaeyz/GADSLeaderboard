package dev.ahmdaeyz.gadsleaderboard.data.network.service.apis

import dev.ahmdaeyz.gadsleaderboard.data.network.networkresponse.HoursLeaderNetworkResponse
import dev.ahmdaeyz.gadsleaderboard.data.network.networkresponse.SkillIQLeaderNetworkResponse
import retrofit2.Response
import retrofit2.http.GET

interface GADSAPI {

    @GET("hours")
    suspend fun getHoursLeaders(): Response<List<HoursLeaderNetworkResponse>>

    @GET("skilliq")
    suspend fun getSkillIQLeaders(): Response<List<SkillIQLeaderNetworkResponse>>
}