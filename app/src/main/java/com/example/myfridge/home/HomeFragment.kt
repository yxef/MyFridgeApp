package com.example.myfridge.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myfridge.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val actionToContact = HomeFragmentDirections.actionHomeFragmentToContactFragment()
    private val actionToFridgeChoice =
        HomeFragmentDirections.actionHomeFragmentToFridgeChoiceFragment()

    private var homeViewModel: HomeViewModel = HomeViewModel()

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

        binding.buttonToContacts.setOnClickListener {
            binding.buttonToContacts.findNavController().navigate(actionToContact)
        }

        binding.buttonToFridgeChoice.setOnClickListener {
            binding.buttonToFridgeChoice.findNavController().navigate(actionToFridgeChoice)
        }

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        // generiamo l'user se non è -1 nelle shared preferences/datastore

/*
        sharedPreferences.edit{
            this.clear()
        }
*/

        // Se l'utente nelle SharedPreferenes è null allora lo generiamo e lo associamo al viewmodel
        if (sharedPreferences.getString("user", "-1").toString() == "-1") {
            sharedPreferences.edit {
                putString("user", UUID.randomUUID().toString()).apply()
            }
            homeViewModel.setUserId(sharedPreferences.getString("user", "-1").toString())
            homeViewModel.createUser()
        }

        Log.d("Dataset", sharedPreferences.getString("user", "-1").toString())

    }
}