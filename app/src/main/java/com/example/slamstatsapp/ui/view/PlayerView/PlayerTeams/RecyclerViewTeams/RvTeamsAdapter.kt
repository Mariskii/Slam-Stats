package com.example.slamstatsapp.ui.view.PlayerView.PlayerTeams.RecyclerViewTeams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.slamstatsapp.R
import com.example.slamstatsapp.data.model.StatsModel
import com.example.slamstatsapp.data.model.TeamModels.TeamPlayerModel
import com.example.slamstatsapp.ui.view.PlayerView.PlayerStats.rvStats.RvStatsViewHolder

class RvTeamsAdapter (var teams:List<TeamPlayerModel>): RecyclerView.Adapter<RvTeamsViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvTeamsViewHolder
    {
        return RvTeamsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team_player,parent,false))
    }

    override fun onBindViewHolder(holder: RvTeamsViewHolder, position: Int) {
        holder.bind(teams[position])
    }

    override fun getItemCount(): Int = teams.size
}