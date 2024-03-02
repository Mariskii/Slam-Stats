package com.example.slamstatsapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.slamstatsapp.R
import com.example.slamstatsapp.databinding.ActivityMainBinding
import com.example.slamstatsapp.ui.view.HomeView.RecyclewViewPlayerMain.RvMainPageAdapter
import com.example.slamstatsapp.ui.viewmodel.PlayerViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private val playerViewModel: PlayerViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView:BottomNavigationView

    //RecyclerView
    private lateinit var adapter: RvMainPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI()
    {
        initNavigation()
    }



    private fun  initNavigation()
    {
        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHost.navController
        binding.mainBottomNavigation.setupWithNavController(navController)
    }
}