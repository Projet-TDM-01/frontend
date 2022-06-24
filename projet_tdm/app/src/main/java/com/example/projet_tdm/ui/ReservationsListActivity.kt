package com.example.projet_tdm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_tdm.R
import com.example.projet_tdm.ViewModels.ReservationsViewModel
import com.example.projet_tdm.adapter.ReservationAdapter

class ReservationsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservations_list)

        val recyclerView = findViewById<RecyclerView>(R.id.reservationsRecyclerView)
        val layoutManager = LinearLayoutManager(this)

        // TODO CHECK IF THE USER LOGGED IN OR NOT
        val reservationsViewModel = ViewModelProvider(this)[ReservationsViewModel::class.java]

        // get the reservations
        reservationsViewModel.getUserReservations("")

        reservationsViewModel.loading.observe(this) { loading ->
            if (loading == true) {
                findViewById<ProgressBar>(R.id.reservations_loading).visibility = View.VISIBLE
            } else {
                findViewById<ProgressBar>(R.id.reservations_loading).visibility = View.GONE
            }
        }
        reservationsViewModel.reservations.observe(this) { reservations ->
            if (reservations != null && reservations.isNotEmpty()) {
                findViewById<TextView>(R.id.no_reservations).visibility = View.GONE
                recyclerView.layoutManager = layoutManager
                val adapter = ReservationAdapter(this, reservations)
                recyclerView.adapter = adapter
            } else {
                // show erreur
                findViewById<TextView>(R.id.no_reservations).visibility = View.VISIBLE
            }
        }
    }
}