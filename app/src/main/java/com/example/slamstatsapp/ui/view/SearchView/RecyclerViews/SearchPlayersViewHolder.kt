package com.example.slamstatsapp.ui.view.SearchView.RecyclerViews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.slamstatsapp.data.model.PlayerModel
import com.example.slamstatsapp.databinding.ItemPlayerSearchedBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class SearchPlayersViewHolder(view: View, listener: rvSearchAdapter.OnItemClickListener): RecyclerView.ViewHolder(view)
{
    private val binding = ItemPlayerSearchedBinding.bind(view)
    private var list:rvSearchAdapter.OnItemClickListener = listener

    fun bind(player: PlayerModel)
    {
        binding.playerName.text = player.nombreCompleto
        Picasso.get().load(player.fotoCabeza).into(binding.playerImage)

        itemView.setOnClickListener{
            list.onItemClick(adapterPosition)
        }
    }
}