package com.example.myfridge.foodlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfridge.data.Food
import com.example.myfridge.network.MyFridgeApi
import kotlinx.coroutines.launch

/**
 * [ViewModel] attached to [FoodListFragment]
 */
class FoodListViewModel : ViewModel() {
    private val fridgeId = 11 // da ottenere dal fragment precedente

    // Stores the status of the request
    private val _status = MutableLiveData<String>()

    // External livedata for the request status
    val status: LiveData<String> = _status

    // Private mutabale Food list
    private val _foodList = MutableLiveData<List<Food>>()

    // Public Food list retrieved with API
    val foodList : LiveData<List<Food>> = _foodList

    /**
     * Calls getFood on init so we can display food immediately
     */
    init {
        getFood(fridgeId)
    }

    /**
     * By using the fridge ID, it gets every single food item in fridge
     */
    private fun getFood(fridgeId: Int) {

        viewModelScope.launch {
            try {
                val listResult = MyFridgeApi.retrofitService.getFoodOfFridge(fridgeId)
                _status.value = "Successful request: ${listResult.size} Foods recieved"
                _foodList.value = listResult

            } catch (e: Exception) {
                Log.d("Dataset", "Failure: ${e.message}")
            }
        }
    }
}