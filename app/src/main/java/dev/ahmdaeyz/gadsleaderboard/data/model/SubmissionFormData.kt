package dev.ahmdaeyz.gadsleaderboard.data.model

import dev.ahmdaeyz.gadsleaderboard.util.SimpleValidationError
import dev.ahmdaeyz.gadsleaderboard.util.extensions.isAGithubUrl
import dev.ahmdaeyz.gadsleaderboard.util.extensions.isEmail

data class SubmissionFormData(
    val firstName: String,
    val lastName: String,
    val email: String,
    val projectGithubUrl: String
) {
    fun validate(): List<SimpleValidationError> {
        val errorsStore = mutableListOf<SimpleValidationError>()
        if (!email.isEmail()) {
            errorsStore.add(
                SimpleValidationError(
                    "email",
                    "Not a valid email."
                )
            )
        }
        if (!projectGithubUrl.isAGithubUrl()) {
            errorsStore.add(
                SimpleValidationError(
                    "projectGithubUrl",
                    "Not a valid Github url."
                )
            )
        }
        return errorsStore
    }
}
