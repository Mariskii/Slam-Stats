package com.example.slamstatsapp.ui.view.PlayerView.PlayerStats.rvStats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.slamstatsapp.R
import com.example.slamstatsapp.data.model.StatsModel

class rvStatsAdapter(var stats:List<StatsModel>): RecyclerView.Adapter<RvStatsViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvStatsViewHolder
    {
        return RvStatsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_stats,parent,false))
    }

    override fun getItemCount(): Int = stats.size

    override fun onBindViewHolder(holder: RvStatsViewHolder, position: Int) {
        holder.bind(stats[position])
    }

}