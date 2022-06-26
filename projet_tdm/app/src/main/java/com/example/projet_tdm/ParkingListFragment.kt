package com.example.projet_tdm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_tdm.ViewModels.ParkingListViewModel
import com.example.projet_tdm.ViewModels.ParkingViewModel
import com.example.projet_tdm.ViewModels.ReservationsViewModel
import com.example.projet_tdm.adapter.ParkingsAdapter
import com.example.projet_tdm.adapter.ReservationAdapter

class ParkingListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.parkingsRecylerView)
        val layoutManager = LinearLayoutManager(view.context)

        // TODO CHECK IF THE USER LOGGED IN OR NOT
        val parkingsListViewModel = ViewModelProvider(this)[ParkingListViewModel::class.java]

        // get the reservations
        parkingsListViewModel.getParkings()

        parkingsListViewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading == true) {
                view.findViewById<ProgressBar>(R.id.parking_loading).visibility = View.VISIBLE
            } else {
                view.findViewById<ProgressBar>(R.id.parking_loading).visibility = View.GONE
            }
        }
        parkingsListViewModel.parkings.observe(viewLifecycleOwner) { parkings ->
            if (parkings != null && parkings.isNotEmpty()) {
                recyclerView.layoutManager = layoutManager
                val adapter = ParkingsAdapter(view.context, parkings)
                recyclerView.adapter = adapter
            } else {
                // show erreur
            }
        }
    }
}