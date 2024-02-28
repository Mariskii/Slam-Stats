package com.example.slamstatsapp.ui.view.HomeView.RecyclewViewPlayerMain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.slamstatsapp.R
import com.example.slamstatsapp.data.model.PlayerModel
import com.example.slamstatsapp.databinding.ItemMainRvPlayerBinding
import com.squareup.picasso.Picasso


class RvMainPageAdapter(private val players: List<PlayerModel>) : RecyclerView.Adapter<RvMainPageViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvMainPageViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main_rv_player, parent, false)
        return RvMainPageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RvMainPageViewHolder, position: Int)
    {
        val currentPlayer = players[position]
        holder.bind(currentPlayer)
    }

    override fun getItemCount() = players.size
}

class RvMainPageViewHolder(view: View) : RecyclerView.ViewHolder(view)
{
    private val binding = ItemMainRvPlayerBinding.bind(view)

    fun bind(player : PlayerModel)
    {
        binding.playerName.text = player.nombreCompleto
        Picasso.get().load(player.fotoCabeza).into(binding.ivPlayer)
    }
}