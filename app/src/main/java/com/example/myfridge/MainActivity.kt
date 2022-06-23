package com.example.myfridge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfridge.adapter.FoodItemAdapter
import com.example.myfridge.data.TestDataset
import com.example.myfridge.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Loads test dataset
        val testDataset = TestDataset().loadTestFoodItems()


        // otteniamo la recycle view
        val recyclerView = binding.recyclerView

        // assegnamo l'adapter
        recyclerView.adapter = FoodItemAdapter(this, testDataset)
    }
}