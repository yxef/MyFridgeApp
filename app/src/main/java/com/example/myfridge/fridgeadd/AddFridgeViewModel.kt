package com.example.myfridge.fridgeadd

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfridge.data.NewFridge
import com.example.myfridge.network.MyFridgeApi
import kotlinx.coroutines.launch

class AddFridgeViewModel : ViewModel() {

    private var fridgeToCreate : NewFridge? = null

    fun postFridge(userId: String, fridgeName: String) {
        createFridge(userId, fridgeName)

        viewModelScope.launch {
            try {
                MyFridgeApi.retrofitService.addNewFridgeOfUser(fridgeToCreate!!)
            } catch (e: Exception){
                Log.d("Dataset", "Failure: ${e.message}")
            }
        }
    }

    private fun createFridge(userId: String, fridgeName: String) {
        fridgeToCreate = NewFridge(
            userId,
            fridgeName
        )
    }
}