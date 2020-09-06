package dev.ahmdaeyz.gadsleaderboard.data.model

data class Score(
    val value: Int,
    val type: ScoreType
) {
    val description: String
        get() = "$value ${type.value}"
}
