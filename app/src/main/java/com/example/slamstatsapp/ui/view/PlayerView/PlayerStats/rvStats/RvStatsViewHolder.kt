package com.example.slamstatsapp.ui.view.PlayerView.PlayerStats.rvStats

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.slamstatsapp.data.model.StatsModel
import com.example.slamstatsapp.databinding.ItemStatsBinding

class RvStatsViewHolder(view: View):RecyclerView.ViewHolder(view)
{
    private val binding = ItemStatsBinding.bind(view)

    fun bind(stats:StatsModel)
    {
        binding.statsSeason.text = stats.season
        binding.playedGames.text = stats.pj.toString()
        binding.assistsPerGame.text = stats.app.toString()
        binding.blocksPerGame.text = stats.blk.toString()
        binding.fieldGoalPerc.text = stats.tc
        binding.freeThrowGoalPerc.text = stats.tl
        binding.pointsPerGame.text = stats.ppp.toString()
        binding.reboundsPerGame.text = stats.rpp.toString()
        binding.threePointsMade.text = stats.pm3.toString()
        binding.perc3.text = stats.perc3
    }
}