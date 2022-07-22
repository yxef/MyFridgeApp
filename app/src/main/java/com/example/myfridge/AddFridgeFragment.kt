package com.example.myfridge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfridge.databinding.FragmentAddFridgeBinding

class addFridgeFragment : Fragment() {

    //TODO("Create addFridge ViewModel")

    private var _binding : FragmentAddFridgeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddFridgeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addFridgeButton.setOnClickListener{
            insertFridgeToDatabase()
        }
    }

    // Invece di inserire nel DB devo prendere le info dal database
    private fun insertFridgeToDatabase() {
        TODO("Not yet implemented")
    }
}