package com.example.myfridge.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfridge.data.User
import com.example.myfridge.network.MyFridgeApi
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    fun setUserId(user: String) {
        _userId.value = user
    }

    fun createUser() {
        viewModelScope.launch {
            try {
                MyFridgeApi.retrofitService.createUserAPI(userId = _userId.value!!)
            } catch (e: Exception) {
                Log.d("Dataset", "Failure: ${e.message}")
            }
        }
    }

}