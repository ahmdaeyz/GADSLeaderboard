package dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard.learningleaders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.ahmdaeyz.gadsleaderboard.data.network.service.NetworkServiceImpl
import dev.ahmdaeyz.gadsleaderboard.databinding.FragmentLearningLeadersBinding
import dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard.adapters.LeadersListAdapter
import dev.ahmdaeyz.gadsleaderboard.util.LeadersResult

class LearningLeadersFragment : Fragment() {

    private lateinit var viewModel: LearningLeadersViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkService = NetworkServiceImpl.getInstance()
        val viewModelFactory = LearningLeadersViewModelFactory(networkService)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(LearningLeadersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLearningLeadersBinding.inflate(inflater, container, false)
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
