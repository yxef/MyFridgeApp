package com.example.myfridge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.myfridge.databinding.FragmentModifyFoodBinding


class ModifyFoodFragment : Fragment() {

    private var _binding : FragmentModifyFoodBinding? = null
    private val binding get() = _binding!!

    private val action = ModifyFoodFragmentDirections.actionModifyFoodFragmentToFoodListFragment()

 

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModifyFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBack.setOnClickListener{
            binding.buttonBack.findNavController().navigate(action)
        }
    }
}