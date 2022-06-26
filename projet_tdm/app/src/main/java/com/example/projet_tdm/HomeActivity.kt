package com.example.projet_tdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.projet_tdm.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val parkingListFragment = ParkingListFragment()
        val userFragment = UserFragment()
        val searchFragment = SearchFragment()

        setCurrentFragment(parkingListFragment)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_parking_list -> setCurrentFragment(parkingListFragment)
                R.id.navigation_user -> setCurrentFragment(userFragment)
                R.id.navigation_search -> setCurrentFragment(searchFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}