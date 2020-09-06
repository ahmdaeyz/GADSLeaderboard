package dev.ahmdaeyz.gadsleaderboard.util

sealed class SubmissionResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : SubmissionResult<T>()
    data class Error(val message: String) : SubmissionResult<Nothing>()
}
