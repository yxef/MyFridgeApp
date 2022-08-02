package com.example.myfridge.foodlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.Food


// Adapter che mostra [Food] data objects
class FoodItemAdapter(
    /*private val context: Context,*/
    // val dataset: LiveData<String>
    private val dataset: LiveData<List<Food>>
) : RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {


    class FoodItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewFoodName: TextView = view.findViewById(R.id.food_name)
        val textViewExpirationDate: TextView = view.findViewById(R.id.food_expiration_date)
        val imageViewIconImageView : ImageView = view.findViewById(R.id.food_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_item, parent, false)

        return FoodItemViewHolder(adapterLayout)
    }


    /**
     *
     * holder.textViewFoodName.text = foodListViewModel.status.value.toString()
     * ho provato queste, ma non funziona passare il viewmodel, devo passare i livedata
     */
    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        Log.d("Dataset", "cum ${dataset.value}")

        //holder.textViewFoodName.text = info.value

        holder.textViewFoodName.text = dataset.value?.get(position)?.foodName ?: "Missing data"
        holder.textViewExpirationDate.text = dataset.value?.get(position)?.expirationDate ?: "Missing Data"
        holder.imageViewIconImageView.contentDescription = dataset.value?.get(position)?.iconId.toString() // da rifare, solo placeholder dove invece di cambiare la icona
    }

    override fun getItemCount(): Int {
        //TODO("non operiamo più su un database interno, il dataset viene preso tramite json dall'internet")
        return dataset.value?.size ?: 0
    }
}