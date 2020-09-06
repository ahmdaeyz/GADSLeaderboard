package dev.ahmdaeyz.gadsleaderboard.ui.main.projectsubmission

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageButton
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import dev.ahmdaeyz.gadsleaderboard.R
import dev.ahmdaeyz.gadsleaderboard.data.model.SubmissionFormData
import dev.ahmdaeyz.gadsleaderboard.data.network.service.NetworkServiceImpl
import dev.ahmdaeyz.gadsleaderboard.databinding.FragmentProjectSubmissionBinding
import dev.ahmdaeyz.gadsleaderboard.ui.main.MainSharedViewModel
import dev.ahmdaeyz.gadsleaderboard.util.SubmissionResult

class ProjectSubmissionFragment : Fragment() {
    private val sharedViewModel by activityViewModels<MainSharedViewModel>()
    private lateinit var viewModel: ProjectSubmissionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkService = NetworkServiceImpl.getInstance()
        val viewModelFactory = ProjectSubmissionViewModelFactory(networkService)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ProjectSubmissionViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProjectSubmissionBinding.inflate(inflater, container, false)

        binding.submitButton.setOnClickListener { submitButton ->
            val dialog = prepareDialog(R.layout.confirm_submission_dialog_layout)
            dialog.show()
            submitButton.visibility = View.GONE
            dialog.window?.findViewById<ImageButton>(R.id.confirmation_dialog_close_button)
                ?.setOnClickListener {
                    dialog.dismiss()
                }
            dialog.window?.findViewById<AppCompatButton>(R.id.confirmation_dialog_yes_button)
                ?.setOnClickListener {
                    val formData = SubmissionFormData(
                        binding.firstNameTextField.text.toString(),
                        binding.lastNameTextField.text.toString(),
                        binding.emailAddressTextField.text.toString(),
                        binding.githubUrlTextField.text.toString()
                    )
                    viewModel.submitProject(formData)
                    dialog.dismiss()
                }
            dialog.setOnDismissListener {
                submitButton.visibility = View.VISIBLE
            }
        }

        viewModel.submission.observe(viewLifecycleOwner) { submissionResult ->
            when (submissionResult) {
                is SubmissionResult.Success -> {
                    val successDialog = prepareDialog(R.layout.successful_submission_layout)
                    successDialog.show()
                    binding.formViews.visibility = View.GONE
                    successDialog.setOnDismissListener {
                        binding.formViews.visibility = View.VISIBLE
                    }
                }
                is SubmissionResult.Error -> {
                    Log.d("SubmissionError", submissionResult.message)
                    val failedDialog = prepareDialog(R.layout.failed_submission_layout)
                    failedDialog.show()
                    binding.formViews.visibility = View.GONE
                    failedDialog.setOnDismissListener {
                        binding.formViews.visibility = View.VISIBLE
                    }
                }
            }
        }
        viewModel.validation.observe(viewLifecycleOwner) { validationErrors ->
            validationErrors.forEach { error ->
                when (error.what) {
                    "email" -> {
                        binding.emailAddressTextField.error = error.why
                    }
                    "projectGithubUrl" -> {
                        binding.githubUrlTextField.error = error.why
                    }
                }
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            sharedViewModel.navigateToLeadersScreen()
        }
        return binding.root
    }

    private fun prepareDialog(@LayoutRes layoutResId: Int): Dialog {
        val dialog = Dialog(requireContext())
        with(dialog) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(layoutResId)
        }
        dialog.window?.setBackgroundDrawable(activity?.getDrawable(R.drawable.submission_dialog_background))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return dialog
    }
}
