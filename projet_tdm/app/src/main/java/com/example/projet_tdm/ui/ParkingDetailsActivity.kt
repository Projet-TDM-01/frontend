package com.example.projet_tdm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projet_tdm.R
import com.example.projet_tdm.databinding.ActivityParkingDetailsBinding

class ParkingDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityParkingDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParkingDetailsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_parking_details)
    }
}