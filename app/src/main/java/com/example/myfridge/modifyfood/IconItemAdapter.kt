package com.example.myfridge.modifyfood


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R

private var selectedIconPosition = -1
private var previousIconPosition = -1

class IconItemAdapter(
    private val dataset: List<Int>
) : RecyclerView.Adapter<IconItemAdapter.IconViewHolder>() {

    class IconViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconImageView: ImageView = view.findViewById(R.id.icon_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.icon_item, parent, false)

        return IconViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        holder.iconImageView.setImageResource(dataset[position])
        holder.iconImageView.setBackgroundColor(holder.iconImageView.context.getColor(R.color.white))


        holder.iconImageView.setOnClickListener {
            previousIconPosition = selectedIconPosition
            selectedIconPosition = holder.adapterPosition

            holder.iconImageView.setBackgroundColor(it.context.getColor(R.color.davys_grey))
            notifyItemChanged(previousIconPosition)

            Toast.makeText(
                it.context,
                "$selectedIconPosition || $previousIconPosition || $position",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}
