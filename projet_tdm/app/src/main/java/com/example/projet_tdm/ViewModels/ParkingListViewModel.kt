package com.example.projet_tdm.ViewModels
import com.example.projet_tdm.modals.Parking


class ParkingListViewModel {
    fun loadData() : ArrayList<Parking> {
        return parkings
    }
    var parkings = ArrayList<Parking>()
}