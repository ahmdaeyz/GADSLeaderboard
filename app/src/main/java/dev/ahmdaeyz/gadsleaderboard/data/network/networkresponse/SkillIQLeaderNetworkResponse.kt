package dev.ahmdaeyz.gadsleaderboard.data.network.networkresponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkillIQLeaderNetworkResponse(
    @SerialName("badgeUrl")
    val badgeUrl: String,
    @SerialName("country")
    val country: String,
    @SerialName("name")
    val name: String,
    @SerialName("score")
    val score: Int
)
