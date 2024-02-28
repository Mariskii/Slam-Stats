package com.example.slamstatsapp.ui.view.SearchView.RecyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.slamstatsapp.R
import com.example.slamstatsapp.data.model.PlayerModel

class rvSearchAdapter(var players: List<PlayerModel>): RecyclerView.Adapter<SearchPlayersViewHolder>()
{
    private lateinit var mListener : OnItemClickListener

    //Interfaz que implementa el método que hará que se pueda clickar sobre un elemento
    interface OnItemClickListener {
        fun onItemClick(itemId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPlayersViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_player_searched, parent, false)
        return SearchPlayersViewHolder(itemView,mListener)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: SearchPlayersViewHolder, position: Int)
    {
        holder.bind(players[position])
    }

    fun onItemClickListener(listener: OnItemClickListener)
    {
        mListener = listener
    }
}