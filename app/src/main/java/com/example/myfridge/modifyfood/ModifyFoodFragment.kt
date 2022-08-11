package com.example.myfridge.modifyfood

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.Food
import com.example.myfridge.databinding.FragmentModifyFoodBinding
import com.google.android.material.datepicker.MaterialDatePicker


class ModifyFoodFragment : Fragment() {

    private val action = ModifyFoodFragmentDirections.actionModifyFoodFragmentToFoodListFragment()


    private var _binding: FragmentModifyFoodBinding? = null
    private val binding get() = _binding!!

    private var addFoodViewModel: AddFoodViewModel = AddFoodViewModel()

    private lateinit var recyclerView: RecyclerView

    // Food item that we will insert into our fridge
    private val food : Food? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModifyFoodBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView

        recyclerView.adapter = IconItemAdapter(addFoodViewModel.iconList)

        // Create the date picker builder, passing the normal datepicker
        val materialDateBuilder: MaterialDatePicker.Builder<*> =
            MaterialDatePicker.Builder
                .datePicker()
                .setTitleText("Select a Date!")

        val materialDatePicker = materialDateBuilder.build() //come in fortnite poggers


        binding.buttonChooseExpirationDate.setOnClickListener {
            activity?.let {
                materialDatePicker.show(
                    it.supportFragmentManager,
                    "MATERIAL_DATE_PICKER"
                )
            }
        }

        materialDatePicker.addOnPositiveButtonClickListener {
            binding.textViewExpirationDateFeedback.text =
                getString(R.string.expiration_feedback, materialDatePicker.headerText)
        }

        binding.buttonConfirmChoice.setOnClickListener {
            binding.buttonConfirmChoice.findNavController().navigate(action)
        }
    }
}