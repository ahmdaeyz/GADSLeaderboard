package dev.ahmdaeyz.gadsleaderboard.ui.main.projectsubmission

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ahmdaeyz.gadsleaderboard.data.model.SubmissionFormData
import dev.ahmdaeyz.gadsleaderboard.data.network.service.NetworkService
import dev.ahmdaeyz.gadsleaderboard.util.SimpleValidationError
import dev.ahmdaeyz.gadsleaderboard.util.SubmissionResult
import kotlinx.coroutines.launch

private const val TAG = "ProjectSubmissionViewModel"

class ProjectSubmissionViewModel(
    private val networkService: NetworkService.Submission
) : ViewModel() {
    private val _submission = MutableLiveData<SubmissionResult<Unit>>()
    val submission: LiveData<SubmissionResult<Unit>>
        get() = _submission

    private val _validation = MutableLiveData<List<SimpleValidationError>>()
    val validation: LiveData<List<SimpleValidationError>>
        get() = _validation

    fun submitProject(
        formData: SubmissionFormData
    ) {
        viewModelScope.launch {
            val validationErrors = formData.validate()
            if (validationErrors.isEmpty()) {
                val submissionResult =
                    networkService.submitProjectAsync(
                        formData.firstName,
                        formData.lastName,
                        formData.email,
                        formData.projectGithubUrl
                    ).await()
                _submission.value = submissionResult
            } else {
                Log.d(TAG, validationErrors.toString())
                _validation.value = validationErrors
            }
        }
    }
}