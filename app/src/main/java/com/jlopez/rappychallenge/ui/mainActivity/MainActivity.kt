package com.jlopez.rappychallenge.ui.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.jlopez.rappychallenge.R
import com.jlopez.rappychallenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
            )
        )

        setupActionBarWithNavController(navController!!, appBarConfiguration!!)

        navController.addOnDestinationChangedListener{ _, destination, _ ->

            when(destination.id){
                R.id.homeFragment ->{
                    showToolbar()
                }
                else ->{
                    hideToolbar()
                }
            }

        }


    }

    private fun showToolbar() {
        binding.toolbar.apply {
            visibility = View.VISIBLE
        }
    }

    private fun hideToolbar() {
        binding.toolbar.apply {
            visibility = View.GONE
        }
    }





}