package com.example.myfridge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.Food


// Adapter che mostra [Food] data objects
class FoodItemAdapter(
    /*private val context: Context,*/
    val dataset: List<Food>
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

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        //holder.textViewFoodName.text = dataset[position].name
        //holder.textViewExpirationDate.text = dataset[position].expiration_date
        //holder.imageViewIconImageView.contentDescription = dataset[position].iconId.toString() // da rifare, solo placeholder dove invece di cambiare la icona
    }

    override fun getItemCount(): Int {
        TODO("non operiamo più su un database interno, il dataset viene preso tramite json dall'internet")
        //return dataset.size
    }
}