package com.example.myfridge.fridgelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfridge.data.Fridge
import com.example.myfridge.network.MyFridgeApi
import kotlinx.coroutines.launch

class FridgeChoiceViewModel : ViewModel() {
    private var _fridgeList = MutableLiveData<List<Fridge>>()
    val fridgeList: LiveData<List<Fridge>> = _fridgeList

    fun getFridges(userId: String) {
        viewModelScope.launch {
            try {
                val listResult = MyFridgeApi.retrofitService.getFridgesOfUser(userId)
                _fridgeList.value = listResult
            } catch (e: Exception) {
                Log.d("Dataset", "Failure: ${e.message}")
            }
        }
    }

    fun deleteFridge(userId: String, fridgeId: Int) {
        viewModelScope.launch {
            try {
                MyFridgeApi.retrofitService.deleteFridgeOfUser(userId, fridgeId)
                getFridges(userId)
            } catch (e: Exception) {
                Log.d("Dataset", "Failure: ${e.message}")
            }
        }
    }

}