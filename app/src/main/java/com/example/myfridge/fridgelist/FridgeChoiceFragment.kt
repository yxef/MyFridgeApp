package com.example.myfridge.fridgelist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.data.Fridge
import com.example.myfridge.databinding.FragmentFridgeChoiceBinding


class FridgeChoiceFragment : Fragment() {

    private var _binding: FragmentFridgeChoiceBinding? = null
    private val binding get() = _binding!!


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
        activity?.title = "Choose your Fridge or Add a new one"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeDirections()

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val moveToFoodList: (Int, String) -> Unit =
            { fridgeId: Int, fridgeName: String ->
                findNavController().navigate(
                    FridgeChoiceFragmentDirections.actionFridgeChoiceFragmentToFoodListFragment(
                        fridgeName,
                        fridgeId
                    )
                )
            }

        recyclerView = binding.recyclerView
        recyclerView.adapter =
            FridgeChoiceAdapter(
                fridgeChoiceViewModel.fridgeList,
                { deleteFridgeOfUser(it) },
                moveToFoodList
            )



        val fridgeObserver = Observer<List<Fridge>> {
            recyclerView.adapter =
                FridgeChoiceAdapter(
                    fridgeChoiceViewModel.fridgeList,
                    { deleteFridgeOfUser(it) },
                    moveToFoodList
                )
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