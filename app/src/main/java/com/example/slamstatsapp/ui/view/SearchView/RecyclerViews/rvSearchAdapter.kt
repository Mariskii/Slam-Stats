package com.example.slamstatsapp.ui.view.SearchView.RecyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.slamstatsapp.R
import com.example.slamstatsapp.data.model.PlayerModel

class rvSearchAdapter(var players: List<PlayerModel>): RecyclerView.Adapter<SearchPlayersViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPlayersViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_player_searched, parent, false)
        return SearchPlayersViewHolder(itemView)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: SearchPlayersViewHolder, position: Int)
    {
        holder.bind(players[position])
    }

}