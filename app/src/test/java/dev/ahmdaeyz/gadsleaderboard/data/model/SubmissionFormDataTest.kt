package dev.ahmdaeyz.gadsleaderboard.data.model

import com.google.common.truth.Truth.assertThat
import dev.ahmdaeyz.gadsleaderboard.util.SimpleValidationError
import org.junit.Test

class SubmissionFormDataTest {

    @Test
    fun testValidateWithInvalidGithubProjectUrl() {
        val submissionFormData = SubmissionFormData(
            "Name",
            "Last",
            "name@gmail.com",
            "https://github.com/ahmdaeyzexample"
        )
        val expected = SimpleValidationError(
            "projectGithubUrl",
            "Not a valid Github url."
        )
        val actual = submissionFormData.validate()
        assertThat(actual).hasSize(1)
        assertThat(actual).containsExactly(expected)
    }

    @Test
    fun testValidateWithInvalidEmail() {
        val submissionFormData = SubmissionFormData(
            "Name",
            "Last",
            "name@",
            "https://github.com/ahmdaeyz/example"
        )
        val expected = SimpleValidationError(
            "email",
            "Not a valid email."
        )
        val actual = submissionFormData.validate()
        assertThat(actual).hasSize(1)
        assertThat(actual).containsExactly(expected)
    }

    @Test
    fun testValidateWithInvalidEmailAndGithubUrl() {
        val submissionFormData = SubmissionFormData(
            "Name",
            "Last",
            "name@",
            "https://github.com/ahmdaeyzexample"
        )
        val expected = listOf(
            SimpleValidationError(
                "email",
                "Not a valid email."
            ),
            SimpleValidationError(
                "projectGithubUrl",
                "Not a valid Github url."
            )
        )
        val actual = submissionFormData.validate()
        assertThat(actual).hasSize(2)
        assertThat(actual).containsExactly(expected.first(), expected.last())
    }
}
