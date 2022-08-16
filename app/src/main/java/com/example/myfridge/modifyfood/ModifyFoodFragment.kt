package com.example.myfridge.modifyfood

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.Food
import com.example.myfridge.databinding.FragmentModifyFoodBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.DateFormat
import java.util.*


class ModifyFoodFragment : Fragment() {

    private val action = ModifyFoodFragmentDirections.actionModifyFoodFragmentToFoodListFragment()


    private var _binding: FragmentModifyFoodBinding? = null
    private val binding get() = _binding!!

    private var addFoodViewModel: AddFoodViewModel = AddFoodViewModel()

    private lateinit var recyclerView: RecyclerView

    private lateinit var selectedDate: String

    // Food item that we will insert into our fridge
    private val food: Food? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModifyFoodBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView

        recyclerView.adapter = IconItemAdapter(addFoodViewModel, addFoodViewModel.iconList)

        val constraintsBuilder =
            CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now())

        // Create the date picker builder, passing the normal datepicker
        val materialDateBuilder: MaterialDatePicker.Builder<*> =
            MaterialDatePicker.Builder
                .datePicker()
                .setTitleText("Select a Date!")
                .setCalendarConstraints(constraintsBuilder.build())


        val materialDatePicker = materialDateBuilder.build() //come in fortnite poggers


        // Shows calendar picker
        binding.buttonChooseExpirationDate.setOnClickListener {
            activity?.let {
                materialDatePicker.show(
                    it.supportFragmentManager,
                    "MATERIAL_DATE_PICKER"
                )
            }
        }

        // gets the expiration date to show to the user and to use when pushing to DB
        materialDatePicker.addOnPositiveButtonClickListener {
            binding.textViewExpirationDateFeedback.text =
                getString(R.string.expiration_feedback, materialDatePicker.headerText)
        }

        binding.buttonConfirmChoice.setOnClickListener {
            addFoodViewModel.createFood(
                fridgeId = 11, // Da rimpiazzare con fridgeId nel bundle
                iconId = addFoodViewModel.selectedIconPosition,
                expirationDate = "22-12-31",
                foodName = binding.foodNameEditText.text.toString()
            )
            Log.d("Dataset", addFoodViewModel.foodToInsert.toString())
            addFoodViewModel.pushFoodToDatabase()
            binding.buttonConfirmChoice.findNavController().navigate(action)
        }
    }
}