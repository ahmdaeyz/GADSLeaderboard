package dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.activityViewModels
import dev.ahmdaeyz.gadsleaderboard.databinding.FragmentLeaderBoardBinding
import dev.ahmdaeyz.gadsleaderboard.ui.main.MainSharedViewModel
import dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard.adapters.LeadersFragmentPagerAdapter

class LeaderBoardFragment : Fragment() {
    private val sharedViewModel by activityViewModels<MainSharedViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLeaderBoardBinding.inflate(inflater, container, false)

        val leadersFragmentPagerAdapter = LeadersFragmentPagerAdapter(
            childFragmentManager,
            FragmentPagerAdapter.POSITION_UNCHANGED
        )
        binding.viewPager.adapter = leadersFragmentPagerAdapter
        binding.tabsLayout.setupWithViewPager(binding.viewPager)

        binding.submitButton.setOnClickListener {
            sharedViewModel.navigateToSubmissionScreen()
        }
        return binding.root
    }
}
