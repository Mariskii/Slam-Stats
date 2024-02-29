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
import com.example.slamstatsapp.R
import com.example.slamstatsapp.databinding.FragmentPlayerBinding
import com.example.slamstatsapp.ui.viewmodel.PlayerViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlayerFragment : Fragment()
{
    private lateinit var binding: FragmentPlayerBinding
    private val playerViewModel: PlayerViewModel by viewModels()
    private val fragmentPlayerargs:PlayerFragmentArgs by navArgs()

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
        observePlayerResult()
        playerViewModel.searchPlayerById(fragmentPlayerargs.idPlayer)
    }

    override fun onStart()
    {
        super.onStart()
        //playerViewModel.searchPlayerById(fragmentPlayerargs.idPlayer)
    }

    private fun observePlayerResult()
    {
        // Recolectar el flujo de estado searchedPlayerById
        lifecycleScope.launch {
            playerViewModel.searchedPlayerById.collect { player ->
                binding.playerName.text = player.nombreCompleto
                binding.playerPosition.text = player.posicion

                if(player.fotoCompleta.isNotEmpty())
                    Picasso.get().load(player.fotoCompleta).resize(binding.playerImage.width, binding.playerImage.height).centerCrop().into(binding.playerImage)
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