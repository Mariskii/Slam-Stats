package com.example.slamstatsapp.ui.view.PlayerView

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.slamstatsapp.ui.view.PlayerView.PlayerStats.StatsFragment
import com.example.slamstatsapp.ui.view.PlayerView.PlayerTeams.PlayerTeamsFragment
import javax.inject.Inject

class ViewPagerAdapter(fragment: Fragment, private var playerId:Int):FragmentStateAdapter(fragment)
{
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment
    {
        return when(position)
        {
            0 -> StatsFragment(playerId)
            1 -> PlayerTeamsFragment(playerId)
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

}