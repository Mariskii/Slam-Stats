package com.example.slamstatsapp.ui.view.HomeView

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slamstatsapp.data.model.PlayerModel
import com.example.slamstatsapp.databinding.FragmentHomeBinding
import com.example.slamstatsapp.ui.view.HomeView.RecyclewViewPlayerMain.RvMainPageAdapter
import com.example.slamstatsapp.ui.view.MainActivity
import com.example.slamstatsapp.ui.viewmodel.PlayerViewModel
import com.example.slamstatsapp.ui.viewmodel.ViewState
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment()
{
    private lateinit var binding: FragmentHomeBinding
    private val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var adapter: RvMainPageAdapter
    private var sugerenciesPlayers:List<PlayerModel> = emptyList()
    private var playerOfTheDay:PlayerModel? = null

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
        initRetryButton()
        return binding.root
    }

    private fun observeResults()
    {
        lifecycleScope.launch{
            combine(
                playerViewModel.stateUI,
                playerViewModel.playerOfTheDay,
                playerViewModel.playersSugerencies
            ){state, playerOD, pSugerencies ->
                when(state)
                {
                    is ViewState.ErrorLoading -> {
                        Toast.makeText(requireContext(),"Error en la conexión",Toast.LENGTH_SHORT).show()
                        hideUI()
                    }
                    ViewState.Loading -> {}
                    is ViewState.Success -> {
                        playerOfTheDay= playerOD
                        sugerenciesPlayers = pSugerencies
                        initUiData()
                    }
                }
            }.collect()
        }
    }

    private fun hideUI()
    {
        binding.mainLoadAnimation.visibility = View.GONE

        binding.homeUI.visibility = View.GONE

        binding.retryButton.visibility = View.VISIBLE

        (activity as? MainActivity)?.hideSplashScreen()
    }

    private fun initRetryButton()
    {
        binding.retryButton.setOnClickListener{
            playerViewModel.onCreate()
            binding.retryButton.visibility = View.GONE
            binding.mainLoadAnimation.visibility = View.VISIBLE
        }
    }

    private fun initUiData()
    {
        binding.mainLoadAnimation.visibility = View.GONE

        adapter = RvMainPageAdapter(sugerenciesPlayers)
        binding.rvSugerencies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSugerencies.adapter = adapter


        binding.playerOfTheDayName.text = playerOfTheDay?.nombreCompleto
        Picasso.get().load(playerOfTheDay?.fotoCompleta).into(binding.playerOfTheDayImage)

        binding.retryButton.visibility = View.GONE
        binding.homeUI.visibility = View.VISIBLE

        //Ocultar SplashScreen
        (activity as? MainActivity)?.hideSplashScreen()
    }
}