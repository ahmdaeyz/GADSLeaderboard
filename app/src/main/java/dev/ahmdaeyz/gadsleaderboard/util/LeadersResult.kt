package dev.ahmdaeyz.gadsleaderboard.util

sealed class LeadersResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : LeadersResult<T>()
    data class Error(val message: String) : LeadersResult<Nothing>()
}
