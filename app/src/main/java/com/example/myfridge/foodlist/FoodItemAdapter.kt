package com.example.myfridge.foodlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.Food
import com.google.android.material.floatingactionbutton.FloatingActionButton

private val iconDrawableId = listOf(
    R.drawable.ic_baby_food,
    R.drawable.ic_canned_food,
    R.drawable.ic_chicken_food,
    R.drawable.ic_crab_food,
    R.drawable.ic_fish_food,
    R.drawable.ic_fruit_orange_food,
    R.drawable.ic_ham_food,
    R.drawable.ic_soda_food,
    R.drawable.ic_vegetables_pumpkin_food,
    R.drawable.ic_wine_food
)

// Adapter per mostrare [Food] data objects
class FoodItemAdapter(
    private val dataset: LiveData<List<Food>>,
    private val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    class FoodItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewFoodName: TextView = view.findViewById(R.id.food_name)
        val textViewExpirationDate: TextView = view.findViewById(R.id.food_expiration_date)
        val imageViewIconImageView: ImageView = view.findViewById(R.id.food_icon)
        val removeFoodButton : FloatingActionButton = view.findViewById(R.id.button_to_remove_food)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_item, parent, false)

        return FoodItemViewHolder(adapterLayout)
    }


    /**
     * Assegnamo i live data se non sono null alle nostre textview, altrimenti assegnamo
     * il placeholder "Missing Data"
     */
    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {

        holder.textViewFoodName.text = dataset.value?.get(position)?.foodName ?: "Missing data"

        holder.textViewExpirationDate.text =
            dataset.value?.get(position)?.expirationDate ?: "Missing Data"

        holder.imageViewIconImageView.setImageResource(
            iconDrawableId[dataset.value?.get(position)?.iconId ?: 1]
        )

        holder.removeFoodButton.setOnClickListener{
            clickListener(dataset.value?.get(position)?.foodId ?: -1)
        }

    }

    override fun getItemCount(): Int {
        // If our dataset has been initialized then we return size otherwise 0
        return dataset.value?.size ?: 0
    }
}