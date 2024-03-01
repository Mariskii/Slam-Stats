package com.example.slamstatsapp.ui.view.PlayerView.RecyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.slamstatsapp.R
import com.example.slamstatsapp.data.model.TrophiesModel.Trophie

class TrophiesPlayerAdapter(var trophies: List<Trophie>): RecyclerView.Adapter<TrophiesPlayerViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrophiesPlayerViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_trophie, parent, false)
        return TrophiesPlayerViewHolder(itemView)
    }

    override fun getItemCount(): Int = trophies.size

    override fun onBindViewHolder(holder: TrophiesPlayerViewHolder, position: Int)
    {
        holder.bind(trophies[position])
    }

}