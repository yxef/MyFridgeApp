package com.example.myfridge.fridgelist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.data.Fridge
import com.example.myfridge.databinding.FragmentFridgeChoiceBinding


class FridgeChoiceFragment : Fragment() {

    private var _binding: FragmentFridgeChoiceBinding? = null
    private val binding get() = _binding!!

    private val actionToFoodList =
        FridgeChoiceFragmentDirections.actionFridgeChoiceFragmentToFoodListFragment()
    private val actionToAddFridge =
        FridgeChoiceFragmentDirections.actionFridgeChoiceFragmentToAddFridgeFragment()
    private val actionBack =
        FridgeChoiceFragmentDirections.actionFridgeChoiceFragmentToHomeFragment()

    private var fridgeChoiceViewModel: FridgeChoiceViewModel = FridgeChoiceViewModel()

    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFridgeChoiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeDirections()

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        recyclerView = binding.recyclerView
        recyclerView.adapter =
            FridgeChoiceAdapter(fridgeChoiceViewModel.fridgeList) { deleteFridgeOfUser(it) }

        val fridgeObserver = Observer<List<Fridge>> {
            recyclerView.adapter =
                FridgeChoiceAdapter(fridgeChoiceViewModel.fridgeList) { deleteFridgeOfUser(it) }
        }

        fridgeChoiceViewModel.fridgeList.observe(viewLifecycleOwner, fridgeObserver)
        fridgeChoiceViewModel.getFridges(sharedPreferences.getString("user", "-1").toString())

    }


    private fun initializeDirections() {
        // Fragment Movement

        binding.buttonToAddFridge.setOnClickListener {
            binding.buttonToAddFridge.findNavController().navigate(actionToAddFridge)
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(actionBack)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun deleteFridgeOfUser(fridgeId: Int) {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        fridgeChoiceViewModel.deleteFridge(
            sharedPreferences.getString("user", "-1").toString(),
            fridgeId
        )

    }


}