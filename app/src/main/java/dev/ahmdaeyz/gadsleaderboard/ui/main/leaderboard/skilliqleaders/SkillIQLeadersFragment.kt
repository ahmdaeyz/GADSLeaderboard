package dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard.skilliqleaders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.ahmdaeyz.gadsleaderboard.data.network.service.NetworkServiceImpl
import dev.ahmdaeyz.gadsleaderboard.databinding.FragmentSkillIQLeadersBinding
import dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard.adapters.LeadersListAdapter
import dev.ahmdaeyz.gadsleaderboard.util.LeadersResult


class SkillIQLeadersFragment : Fragment() {

    private lateinit var viewModel: SkillIQLeadersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkService = NetworkServiceImpl.getInstance()
        val viewModelFactory = SkillIQLeadersViewModelFactory(networkService)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(SkillIQLeadersViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSkillIQLeadersBinding.inflate(inflater, container, false)
        val adapter = LeadersListAdapter()
        binding.leadersList.adapter = adapter
        viewModel.leaders.observe(viewLifecycleOwner) { leadersListResult ->
            binding.loadingIndicator.visibility = View.GONE
            when (leadersListResult) {
                is LeadersResult.Success -> {
                    adapter.bindItems(leadersListResult.data)
                }
                is LeadersResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "There is an error, ${leadersListResult.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        return binding.root
    }

}