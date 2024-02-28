package com.example.slamstatsapp.ui.view.SearchView

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slamstatsapp.databinding.FragmentSearchBinding
import com.example.slamstatsapp.ui.view.HomeView.RecyclewViewPlayerMain.RvMainPageAdapter
import com.example.slamstatsapp.ui.view.SearchView.RecyclerViews.rvSearchAdapter
import com.example.slamstatsapp.ui.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), OnQueryTextListener
{
    private lateinit var binding: FragmentSearchBinding
    private val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var adapter: rvSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeResult()
        binding.svPlayers.setOnQueryTextListener(this)
    }


    private fun observeResult()
    {
        // Recolectar el flujo de estado searchedPlayers
        lifecycleScope.launch {
            playerViewModel.searchedPlayers.collect { players ->
                // Actualizar UI con la lista de jugadores
                adapter.players = players
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onQueryTextChange(query: String?): Boolean
    {
        var changed = false

        if(!query.isNullOrEmpty())
        {
            //Buscar los jugadores
            playerViewModel.searchPlayers(query)
            changed = true
        }

        return changed
    }

    private fun initRecyclerView()
    {
        adapter = rvSearchAdapter(emptyList())
        binding.rvSearchedPlayers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvSearchedPlayers.adapter = adapter
    }

    //Método no implementado ya que no necesito hacer nada en este estado
    override fun onQueryTextSubmit(query: String?): Boolean
    {
        return true
    }
}