package com.example.slamstatsapp.ui.view.PlayerView.RecyclerViews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.slamstatsapp.data.model.TrophiesModel.Trophie
import com.example.slamstatsapp.databinding.ItemTrophieBinding

class TrophiesPlayerViewHolder(view: View) : RecyclerView.ViewHolder(view)
{
    private val binding = ItemTrophieBinding.bind(view)

    fun bind(trophie: Trophie)
    {
        if(trophie.quantity!! > 0)
        {
            binding.trophieImage.setImageResource(trophie.imageTrophie)
            binding.trophieQuantity.text = "x"+trophie.quantity.toString()
        }
    }
}