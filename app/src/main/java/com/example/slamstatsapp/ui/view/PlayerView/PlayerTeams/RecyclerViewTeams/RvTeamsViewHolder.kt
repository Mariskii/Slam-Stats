package com.example.slamstatsapp.ui.view.PlayerView.PlayerTeams.RecyclerViewTeams

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.slamstatsapp.data.model.StatsModel
import com.example.slamstatsapp.data.model.TeamModels.TeamPlayerModel
import com.example.slamstatsapp.databinding.ItemStatsBinding
import com.example.slamstatsapp.databinding.ItemTeamPlayerBinding
import com.squareup.picasso.Picasso

class RvTeamsViewHolder (view: View): RecyclerView.ViewHolder(view)
{
    private val binding = ItemTeamPlayerBinding.bind(view)

    fun bind(team: TeamPlayerModel)
    {
        binding.teamName.text = team.nombreEquipo
        Picasso.get().load(team.imagenEquipo).into(binding.teamImage)
    }
}