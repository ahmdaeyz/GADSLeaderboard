package dev.ahmdaeyz.gadsleaderboard.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import dev.ahmdaeyz.gadsleaderboard.R
import dev.ahmdaeyz.gadsleaderboard.data.network.service.NetworkServiceImpl
import dev.ahmdaeyz.gadsleaderboard.data.network.service.interceptors.ConnectivityInterceptor
import dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard.LeaderBoardFragment
import dev.ahmdaeyz.gadsleaderboard.ui.main.projectsubmission.ProjectSubmissionFragment
import dev.ahmdaeyz.gadsleaderboard.util.extensions.isConnected
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private val sharedViewModel by viewModels<MainSharedViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        lifecycleScope.launchWhenResumed {
            this@MainActivity.isConnected().collect { connected ->
                if (connected) {
                    navigateToLeadersBoard(fragmentManager)
                    sharedViewModel.shouldNavigateToLeadersScreen.observe(this@MainActivity) { shouldNavigate ->
                        if (shouldNavigate) {
                            navigateToLeadersBoard(fragmentManager)
                        }
                    }
                    sharedViewModel.shouldNavigateToSubmissionScreen.observe(this@MainActivity) { shouldNavigate ->
                        if (shouldNavigate) {
                            Log.d("NavigatingToSubmission", shouldNavigate.toString())
                            navigateToProjectSubmission(fragmentManager)
                        }
                    }
                } else {
                    navigateToNoConnection(fragmentManager)
                }
            }
        }


    }

    private fun navigateToProjectSubmission(fragmentManager: FragmentManager) {
        val projectSubmissionFragment = ProjectSubmissionFragment()
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, projectSubmissionFragment)
            .addToBackStack("submissionRoute")
            .commit()
    }

    private fun navigateToLeadersBoard(fragmentManager: FragmentManager) {
        val leadersBoardFragment = LeaderBoardFragment()
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, leadersBoardFragment)
            .commit()
    }

    private fun navigateToNoConnection(fragmentManager: FragmentManager) {
        val noConnectionFragment = NoConnectionFragment()
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, noConnectionFragment)
            .commit()
    }

}