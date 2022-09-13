package com.example.myfridge.fridgelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.Fridge
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FridgeChoiceAdapter(
    private val dataset: LiveData<List<Fridge>>,
    private val clickListener: (Int) -> Unit,
    private val moveListener: (Int, String) -> Unit
) : RecyclerView.Adapter<FridgeChoiceAdapter.FridgeItemViewHolder>() {

    class FridgeItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val fridgeName: TextView = view.findViewById(R.id.fridge_name)
        val fridgeIconImageView: ImageView = view.findViewById(R.id.fridge_icon)
        val removeFridgeButton: FloatingActionButton =
            view.findViewById(R.id.button_to_remove_fridge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FridgeItemViewHolder {

        return FridgeItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fridge_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FridgeItemViewHolder, position: Int) {
        holder.fridgeName.text = dataset.value?.get(position)?.name ?: "Missing Data"
        holder.fridgeIconImageView.setImageResource(R.drawable.ic_refrigerator_svgrepo_com)

        holder.removeFridgeButton.setOnClickListener {
            // passiamo il corrente fridgeId
            clickListener(dataset.value?.get(position)?.id ?: -1)
        }

        holder.itemView.setOnClickListener {
            moveListener(
                dataset.value?.get(position)?.id ?: -1,
                dataset.value?.get(position)?.name ?: ""
            )
        }
    }

    override fun getItemCount(): Int {
        return dataset.value?.size ?: 0
    }

}