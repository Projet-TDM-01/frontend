package com.example.projet_tdm.ViewModels
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projet_tdm.entity.User
import com.example.projet_tdm.retrofit.authentification
import kotlinx.coroutines.*
import retrofit2.http.FieldMap

class UserViewModel : ViewModel(){
        var users = MutableLiveData<List<User>>()
        var loading = MutableLiveData<Boolean>()
        val errorMessage = MutableLiveData<String>()
        val registerStatus=MutableLiveData<Boolean>()
        var menu: Menu? =null
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            CoroutineScope(Dispatchers.Main).launch {
                onError(throwable.localizedMessage.toString())
            }
        }
        fun loginUserEmail(@FieldMap data: Map<String, String>) {
            loading.value = true
            if (users.value == null || users.value!!.isEmpty()) {
                CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                    val response = authentification.createInstance().loginUserEmail(data)
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful && response.body() != null) {
                            loading.value = false
                            users.postValue(response.body())
                        } else {
                            onError(response.message())
                        }
                    }
                }
            }
        }
        fun loginUserPhone(@FieldMap data: Map<String, String>) {
            loading.value = true
            if (users.value == null || users.value!!.isEmpty()) {
                CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                    val response =
                        authentification.createInstance().loginUserPhone(data)
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful && response.body() != null) {
                            loading.value = false
                            users.postValue(response.body())
                        } else {
                            onError(response.message())
                        }
                    }
                }
            }
        }
        fun regiserUser(@FieldMap data: Map<String, String>) {
            loading.value = true
            CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = authentification.createInstance().registerUser(data)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful ) {
                        loading.value = false
                        registerStatus.value=true
                    } else {
                        registerStatus.value=false
                        onError(response.message())
                    }
                }
            }
        }
        private fun onError(message: String) {
            errorMessage.postValue( message)
            loading.value = false
        }
    }
}