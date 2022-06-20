package com.example.projet_tdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.projet_tdm.modals.Onboarding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {

    var viewPagerAdapter : ViewPagerAdapter? = null
    var tabLayout : TabLayout? = null
    var onboardingViewPager : ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        tabLayout=findViewById(R.id.tab_indicator)

        // add some data

        val onboardingData :MutableList<Onboarding> = ArrayList()
        onboardingData.add(Onboarding("Find Nearby Parking","Easily find the nearby parking spot to you, of the location you are going to.",R.drawable.onboarding1))
        onboardingData.add(Onboarding("Book and Park","Enjoy thousands of confortable and spacious parking spaces for your vehicule.",R.drawable.onboarding2))
        onboardingData.add(Onboarding("Extend Time","When your paring time is up, it's easy to add more time.",R.drawable.onboarding3))

        setOnBoardingViewPagerAdapter(onboardingData)


        skipbtn.setOnClickListener{
            intent = Intent(this, com.example.projet_tdm.LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private  fun setOnBoardingViewPagerAdapter(onboardingData : List<Onboarding>){
        onboardingViewPager= findViewById(R.id.screenPager)
        viewPagerAdapter= ViewPagerAdapter(this,onboardingData)
        onboardingViewPager!!.adapter = viewPagerAdapter
        tabLayout?.setupWithViewPager(onboardingViewPager)
    }
}