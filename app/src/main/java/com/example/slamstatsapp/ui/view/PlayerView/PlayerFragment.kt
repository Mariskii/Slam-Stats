package com.example.slamstatsapp.ui.view.PlayerView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.slamstatsapp.R
import com.example.slamstatsapp.databinding.FragmentPlayerBinding


class PlayerFragment : Fragment()
{
    private lateinit var binding: FragmentPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayerBinding.inflate(inflater,container,false)

        return inflater.inflate(R.layout.fragment_player, container, false)
    }
}