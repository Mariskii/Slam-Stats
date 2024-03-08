package com.example.slamstatsapp.ui.view.PlayerView.PlayerTeams

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slamstatsapp.R
import com.example.slamstatsapp.databinding.FragmentPlayerTeamsBinding
import com.example.slamstatsapp.ui.view.PlayerView.PlayerStats.rvStats.rvStatsAdapter
import com.example.slamstatsapp.ui.view.PlayerView.PlayerTeams.RecyclerViewTeams.RvTeamsAdapter
import com.example.slamstatsapp.ui.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlayerTeamsFragment(private var playerId:Int=0) : Fragment() {

    private lateinit var binding: FragmentPlayerTeamsBinding
    private val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var adapter: RvTeamsAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        if(playerId > 0)
            playerViewModel.getPlayerTeams(playerId)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayerTeamsBinding.inflate(inflater,container,false)

        initRecyclerView()

        observeResults()

        return binding.root
    }

    private fun initRecyclerView()
    {
        adapter = RvTeamsAdapter(emptyList())
        binding.rvPlayerTeams.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvPlayerTeams.adapter = adapter
    }

    private fun observeResults()
    {
        lifecycleScope.launch {
            playerViewModel.teamsPlayer.collect{playerTeams ->
                adapter.teams = playerTeams
                adapter.notifyDataSetChanged()
            }
        }
    }
}