package com.example.myfridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myfridge.databinding.FragmentFridgeChoiceBinding


class FridgeChoiceFragment : Fragment() {

    private var _binding : FragmentFridgeChoiceBinding? = null
    private val binding get() = _binding!!

    private val actionToFoodList = FridgeChoiceFragmentDirections.actionFridgeChoiceFragmentToFoodListFragment()
    private val actionToAddFridge = FridgeChoiceFragmentDirections.actionFridgeChoiceFragmentToAddFridgeFragment()


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
        binding.buttonToFoodList.setOnClickListener{
            binding.buttonToFoodList.findNavController().navigate(actionToFoodList)
        }

        binding.buttonToAddFridge.setOnClickListener{
            binding.buttonToAddFridge.findNavController().navigate(actionToAddFridge)
        }
    }


}