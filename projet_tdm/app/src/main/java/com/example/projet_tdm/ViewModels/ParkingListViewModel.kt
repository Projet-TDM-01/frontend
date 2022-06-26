package com.example.projet_tdm.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projet_tdm.entity.Parking
import com.example.projet_tdm.retrofit.ParkingEndPoint
import kotlinx.coroutines.*


class ParkingListViewModel : ViewModel() {
    var parkings = MutableLiveData<List<Parking>>()
    var loading = MutableLiveData<Boolean>()
    var message = MutableLiveData<String>()

    fun getParkings() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            // traitement de l’exception
            message.value = "Une erreur s'est produit"
        }
        loading.value = true
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = ParkingEndPoint.createEndpoint().getParkings()
            withContext(Dispatchers.Main) {
                loading.value = false
                if (response.isSuccessful) {
                    // or response.code() == 200
                    val data = response.body()
                    Log.d("data", data.toString())
                    if (data != null) {
                        parkings.value = data!!
                    } else {
                        message.value = data.toString()
                    }
                } else {
                    message.value = "Une erreur s'est produit"
                }
            }
        }
    }
}