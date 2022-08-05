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

private val iconNames = listOf<String>(
    "ic_baby_food",
    "ic_canned_food",
    "ic_chicken_food",
    "ic_crab_food",
    "ic_fish_food",
    "ic_fruit_orange_food",
    "ic_ham_food",
    "ic_jar_food",
    "ic_soda_food",
    "ic_vegetables_pumpkin_food",
    "ic_wine_food"
)

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

// Adapter che mostra [Food] data objects
class FoodItemAdapter(
    /*private val context: Context,*/
    // val dataset: LiveData<String>
    private val dataset: LiveData<List<Food>>
) : RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    class FoodItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewFoodName: TextView = view.findViewById(R.id.food_name)
        val textViewExpirationDate: TextView = view.findViewById(R.id.food_expiration_date)
        val imageViewIconImageView: ImageView = view.findViewById(R.id.food_icon)
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

        Log.d("Dataset", "Id Drawables: ${iconDrawableId.toString()}")

        holder.textViewFoodName.text = dataset.value?.get(position)?.foodName ?: "Missing data"
        holder.textViewExpirationDate.text =
            dataset.value?.get(position)?.expirationDate ?: "Missing Data"
        holder.imageViewIconImageView.contentDescription =
            dataset.value?.get(position)?.iconId.toString()

        //holder.imageViewIconImageView.setImageResource(R.drawable.ic_baby_food)
        holder.imageViewIconImageView.setImageResource(iconDrawableId[dataset.value?.get(position)?.iconId ?: 0])
            //dataset.value?.get(position)?.iconId ?: 2131165341

    }

    override fun getItemCount(): Int {
        //TODO("non operiamo più su un database interno, il dataset viene preso tramite json dall'internet")
        return dataset.value?.size ?: 0
    }
}