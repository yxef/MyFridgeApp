package com.example.myfridge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myfridge.data.Food
import com.example.myfridge.data.dao.FoodDao
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

/*
* View Model per interfacciarci al DAO, utilizzata per contenere le informazioni dell'app,
* permette di creare una chiara separazione dei compiti limitando l'accesso al DAO in base
* all'esigenze della schermata. Per esempio, se il nostro DAO fornisce una funzione per accedere
* alla lista dei frigoriferi, qui non ci serve dato che andiamo a stampare solo gli alimenti
* di un frigo. Quindi direttamente non permettiamo l'accesso alla nostra view di interagire
* con quella funzione, usando il ViewModel come intermezzo filtro.
* TLDR: La classe ViewModel contiene i dati relativi ad una View, la UI di un'app.
* View <--> ViewModel <--> Model */

class FoodListViewModel(private val foodDao: FoodDao) : ViewModel() {
    private fun insertFood(food: Food) {
        viewModelScope.launch {
            foodDao.insert(food)
        }
    }

    private fun getNewFoodEntry(id : Int, name: String, expiration_date: String, icon_id: Int, fridgeId : Int) : Food{
        return Food(
            id = id,
            fridgeId = fridgeId,
            name = name,
            expiration_date = expiration_date,
            iconId = icon_id
        )
    }

    private fun addNewFood(id : Int, name: String, expiration_date: String, icon_id: Int){
        val newFood = getNewFoodEntry(id, name, expiration_date, icon_id, 0)
        insertFood(newFood)
    }
}

/*
* Questa Factory instanzia il ViewModel per renderlo lifecycle aware al posto del fragment, dato
* che quello si deve occupare solo del codice relativo alla UI*/
class FoodViewModelFactory(
    private val foodDao: FoodDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FoodListViewModel(foodDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}