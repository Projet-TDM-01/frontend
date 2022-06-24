package com.example.projet_tdm.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.projet_tdm.R
import com.example.projet_tdm.ViewModels.ParkingViewModel
import com.example.projet_tdm.databinding.ActivityParkingDetailsBinding
import java.time.LocalDateTime

class ParkingDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityParkingDetailsBinding

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParkingDetailsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_parking_details)

        val parkingViewModel = ViewModelProvider(this)[ParkingViewModel::class.java]
        val parkingId = intent.extras!!.getString("parking_id");

        if (parkingId != null) {
            parkingViewModel.getParkingById(parkingId)
        }

        parkingViewModel.loading.observe(this) { loading ->
            if (loading == true) {
                findViewById<ProgressBar>(R.id.details_loading).visibility = View.VISIBLE
            } else {
                findViewById<ProgressBar>(R.id.details_loading).visibility = View.GONE
            }
        }
        parkingViewModel.parking.observe(this) { parking ->
            if (parking != null) {
                Glide.with(this).load(parking.imglink)
                    .apply(RequestOptions())
                    .placeholder(R.drawable.parking)
                    .into(binding.parkingImg)

                findViewById<TextView>(R.id.nom_parking).text = parking.nom
                findViewById<TextView>(R.id.parking_commune).text = parking.commune
                findViewById<TextView>(R.id.parking_taux).text = "Taux ${(parking.reserved?.times(100))?.div(
                    parking.nbPlace
                )}%"

                val time = LocalDateTime.now()
                if (time.hour > parking.horraireOuver && time.hour < parking.horraireFerm)
                    findViewById<TextView>(R.id.parking_status).text = "OUVERT"
                else
                    findViewById<TextView>(R.id.parking_status).text  = "FERMER"
                findViewById<TextView>(R.id.parking_start_hour).text  = "${parking.horraireOuver}:00"
                findViewById<TextView>(R.id.parking_end_hour).text  = "${parking.horraireFerm}:00"
                findViewById<TextView>(R.id.parking_prix).text  = "${parking.tarifHeure}"
            } else {
                // show erreur
            }
        }
    }
}