package com.example.slamstatsapp.ui.view.PlayerView.PlayerStats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slamstatsapp.R
import com.example.slamstatsapp.data.model.PlayerModel
import com.example.slamstatsapp.data.model.StatsModel
import com.example.slamstatsapp.databinding.FragmentPlayerBinding
import com.example.slamstatsapp.databinding.FragmentStatsBinding
import com.example.slamstatsapp.domain.GetPlayersUseCase
import com.example.slamstatsapp.domain.GetStatsPlayersUseCase
import com.example.slamstatsapp.ui.view.PlayerView.PlayerFragmentArgs
import com.example.slamstatsapp.ui.view.PlayerView.PlayerStats.rvStats.rvStatsAdapter
import com.example.slamstatsapp.ui.view.SearchView.RecyclerViews.rvSearchAdapter
import com.example.slamstatsapp.ui.viewmodel.StatsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class StatsFragment(private var playerId:Int=0) : Fragment()
{
    private val statsViewModel:StatsViewModel by viewModels()
    private lateinit var binding: FragmentStatsBinding
    private lateinit var adapter:rvStatsAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        if(playerId > 0)
            statsViewModel.searchPlayerStats(playerId)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentStatsBinding.inflate(inflater,container,false)
        initRecyclerView()

        observeStatsResult()
        return binding.root
    }

    private fun observeStatsResult()
    {
        // Recolectar el flujo de estado searchedPlayerById
        lifecycleScope.launch {
            statsViewModel.playerStats.collect{statsCollected ->
                adapter.stats = statsCollected
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun initRecyclerView()
    {
        adapter = rvStatsAdapter(emptyList())
        binding.rvStats.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvStats.adapter = adapter
    }
}