package com.example.slamstatsapp.ui.view.SearchView.RecyclerViews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.slamstatsapp.data.model.PlayerModel
import com.example.slamstatsapp.databinding.ItemPlayerSearchedBinding
import com.squareup.picasso.Picasso

class SearchPlayersViewHolder(view: View): RecyclerView.ViewHolder(view)
{
    private val binding = ItemPlayerSearchedBinding.bind(view)

    fun bind(player: PlayerModel)
    {
        binding.playerName.text = player.nombreCompleto
        Picasso.get().load(player.fotoCabeza).into(binding.playerImage)
    }
}