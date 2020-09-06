package dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard.learningleaders.LearningLeadersFragment
import dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard.skilliqleaders.SkillIQLeadersFragment

class LeadersFragmentPagerAdapter(fragmentManager: FragmentManager, behaviorMode: Int) :
    FragmentPagerAdapter(fragmentManager, behaviorMode) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LearningLeadersFragment()
            1 -> SkillIQLeadersFragment()
            else -> LearningLeadersFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Learning Leaders"
            1 -> "Skill IQ Leaders"
            else -> "Learning Leaders"
        }
    }

    override fun getCount(): Int = 2
}