package com.example.slamstatsapp.ui.view.PlayerView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slamstatsapp.R
import com.example.slamstatsapp.data.model.PlayerModel
import com.example.slamstatsapp.databinding.FragmentPlayerBinding
import com.example.slamstatsapp.ui.view.PlayerView.RecyclerViews.TrophiesPlayerAdapter
import com.example.slamstatsapp.ui.view.SearchView.RecyclerViews.rvSearchAdapter
import com.example.slamstatsapp.ui.viewmodel.PlayerViewModel
import com.example.slamstatsapp.ui.viewmodel.TrophiesViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlayerFragment : Fragment()
{
    private lateinit var binding: FragmentPlayerBinding
    private val playerViewModel: PlayerViewModel by viewModels()
    private val trophiesViewModel:TrophiesViewModel by viewModels()
    private val fragmentPlayerargs:PlayerFragmentArgs by navArgs()

    private lateinit var adapterTrophies:TrophiesPlayerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPlayerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        initUI()


        initTrophiesRecyclerView()

        observePlayerResult()
        observeTrophiesResult()

        playerViewModel.searchPlayerById(fragmentPlayerargs.idPlayer)
        trophiesViewModel.getTrophiesByPlayerId(fragmentPlayerargs.idPlayer)
    }

    private fun observePlayerResult()
    {
        // Recolectar el flujo de estado searchedPlayerById
        lifecycleScope.launch {
            playerViewModel.searchedPlayerById.collect { player ->
                binding.playerName.text = "#"+player.dorsal+" "+player.nombreCompleto
                binding.playerPosition.text = player.posicion
                binding.playerHeight.text = player.altura
                binding.playerBirth.text = player.fnacimiento
                binding.playerWeight.text = player.peso
                binding.playerNationality.text = player.nacionalidad

                if(player.fotoCompleta.isNotEmpty())
                {
                   Picasso.get().load(player.fotoCompleta).into(binding.playerImage)
                }

            }
        }
    }

    private fun initTrophiesRecyclerView()
    {
        adapterTrophies = TrophiesPlayerAdapter(emptyList())
        binding.rvPlayerTrophies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPlayerTrophies.adapter = adapterTrophies
    }

    private fun observeTrophiesResult()
    {
        lifecycleScope.launch {
            trophiesViewModel.playerTrophies.collect{trophies ->
                if (trophies != null)
                {
                    adapterTrophies.trophies = trophies.trophies
                    adapterTrophies.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initUI()
    {
        binding.backArrow.setOnClickListener{
            findNavController().navigateUp()
        }
    }
}