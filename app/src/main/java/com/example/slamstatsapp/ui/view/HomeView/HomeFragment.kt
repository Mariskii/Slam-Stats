package com.example.slamstatsapp.ui.view.HomeView

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slamstatsapp.databinding.FragmentHomeBinding
import com.example.slamstatsapp.ui.view.HomeView.RecyclewViewPlayerMain.RvMainPageAdapter
import com.example.slamstatsapp.ui.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment()
{
    private lateinit var binding: FragmentHomeBinding
    private val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var adapter: RvMainPageAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

    }

    //Cuando se crea el fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    //Cuando la vista está creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViewSugerencies()
    }

    private fun setUpRecyclerViewSugerencies()
    {
        playerViewModel.onCreate()

        playerViewModel.playerModel.observe(viewLifecycleOwner, Observer { players ->
            adapter = RvMainPageAdapter(players ?: emptyList())
            binding.rvSugerencies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvSugerencies.adapter = adapter
        }
        )
    }
}