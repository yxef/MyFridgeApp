package com.example.myfridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myfridge.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val actionToContact = HomeFragmentDirections.actionHomeFragmentToContactFragment()
    private val actionToFridgeChoice = HomeFragmentDirections.actionHomeFragmentToFridgeChoiceFragment()
    private val actionToAddFridge = HomeFragmentDirections.actionHomeFragmentToAddFridgeFragment()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonToContacts.setOnClickListener{
            binding.buttonToContacts.findNavController().navigate(actionToContact)
        }

        binding.buttonToFridgeChoice.setOnClickListener{
            binding.buttonToFridgeChoice.findNavController().navigate(actionToFridgeChoice)
        }

        binding.buttonToAddFridge.setOnClickListener{
            binding.buttonToAddFridge.findNavController().navigate(actionToAddFridge)
        }
    }
}