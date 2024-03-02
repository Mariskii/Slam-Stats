package com.example.slamstatsapp.ui.view.HomeView

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slamstatsapp.databinding.FragmentHomeBinding
import com.example.slamstatsapp.ui.view.HomeView.RecyclewViewPlayerMain.RvMainPageAdapter
import com.example.slamstatsapp.ui.view.MainActivity
import com.example.slamstatsapp.ui.viewmodel.PlayerViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment()
{
    private lateinit var binding: FragmentHomeBinding
    private val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var adapter: RvMainPageAdapter

    override fun onAttach(context: Context)
    {
        playerViewModel.onCreate()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        observeResults()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        //Ocultar SplashScreen
        (activity as? MainActivity)?.hideSplashScreen()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeResults()
    {
        playerViewModel.playerModel.observe(viewLifecycleOwner, Observer { players ->
            adapter = RvMainPageAdapter(players ?: emptyList())
            binding.rvSugerencies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvSugerencies.adapter = adapter
        }
        )

        lifecycleScope.launch {
            playerViewModel.playerOfTheDay.collect { player ->
                binding.playerOfTheDayName.text = player?.nombreCompleto
                Picasso.get().load(player?.fotoCompleta).into(binding.playerOfTheDayImage)
            }
        }
    }
}