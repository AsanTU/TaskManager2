package com.dacha.taskmanager1

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dacha.taskmanager1.data.local.PrefOnBoarding
import com.dacha.taskmanager1.databinding.ActivityMainBinding
import com.dacha.taskmanager1.databinding.FragmentProfileBinding

class MainActivity : AppCompatActivity() {
    private lateinit var prefOnBoard : PrefOnBoarding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        prefOnBoard = PrefOnBoarding(this)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        if (!prefOnBoard.isonBoardingShow()){
            navController.navigate(R.id.onBoardinfFragment)
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.taskFragment
            )
        )

        val arrayList = arrayListOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
            R.id.navigation_profile
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.removeOnDestinationChangedListener { controller, destination, arguments ->
            navView.isInvisible = arrayList.contains(destination.id)
            if(destination.id == R.id.onBoardinfFragment){
                supportActionBar?.hide()
            }else supportActionBar?.show()

        }

    }

}