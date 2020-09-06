package dev.ahmdaeyz.gadsleaderboard.data.network.networkresponse


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HoursLeaderNetworkResponse(
    @SerialName("badgeUrl")
    val badgeUrl: String,
    @SerialName("country")
    val country: String,
    @SerialName("hours")
    val hours: Int,
    @SerialName("name")
    val name: String
)