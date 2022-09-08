package com.example.myfridge.fridgeadd

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myfridge.databinding.FragmentAddFridgeBinding
import com.example.myfridge.foodlist.FoodListFragmentArgs

class AddFridgeFragment : Fragment() {
    private var _binding: FragmentAddFridgeBinding? = null
    private val binding get() = _binding!!

    private val actionToFridgeChoice =
        AddFridgeFragmentDirections.actionAddFridgeFragmentToFridgeChoiceFragment()

    private var addFridgeViewModel: AddFridgeViewModel = AddFridgeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddFridgeBinding.inflate(inflater, container, false)
        activity?.title = "Add a new Fridge"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeDirections()

        binding.addFridgeButton.setOnClickListener {
            insertFridgeToDatabase(binding.insertFridgeNameEditText.text.toString())
        }
    }

    private fun initializeDirections(){

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(actionToFridgeChoice)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    // Invece di inserire nel DB devo prendere le info dal database
    private fun insertFridgeToDatabase(fridgeName: String) {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        addFridgeViewModel.postFridge(
            sharedPreferences.getString("user", "-1").toString(),
            fridgeName
        )
        moveToFridgeChoice()
    }

    private fun moveToFridgeChoice() {
        findNavController().navigate(actionToFridgeChoice)
    }
}