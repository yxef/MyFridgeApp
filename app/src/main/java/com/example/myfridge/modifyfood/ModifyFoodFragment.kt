package com.example.myfridge.modifyfood

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.databinding.FragmentModifyFoodBinding
import com.example.myfridge.foodlist.FoodListFragmentArgs
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class ModifyFoodFragment : Fragment() {

    private val args: FoodListFragmentArgs by navArgs()

    private var _binding: FragmentModifyFoodBinding? = null
    private val binding get() = _binding!!

    private var addFoodViewModel: AddFoodViewModel = AddFoodViewModel()

    private lateinit var recyclerView: RecyclerView

    private lateinit var selectedDate: String

    //    private val formatter =DateTimeFormatter.ofPattern("dd-MMMM-yy")
    private val formatter = SimpleDateFormat("dd/MM/yyyy", Locale("it"))

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
        val action =
            ModifyFoodFragmentDirections.actionModifyFoodFragmentToFoodListFragment(args.fridgeId)

        val callback = object : OnBackPressedCallback(true) {

            override fun handleOnBackPressed() {
                findNavController().navigate(action)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


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

            selectedDate = formatter.format(materialDatePicker.selection)
            Log.d("Dataset", "$selectedDate || ${materialDatePicker.selection}")
        }



        binding.buttonConfirmChoice.setOnClickListener {

            addFoodViewModel.createFood(
                fridgeId = args.fridgeId, // Da rimpiazzare con fridgeId
                iconId = addFoodViewModel.selectedIconPosition,
                expirationDate = selectedDate,
                foodName = binding.foodNameEditText.text.toString()
            )
            Log.d("Dataset", addFoodViewModel.foodToInsert.toString())
            addFoodViewModel.pushFoodToDatabase()
            binding.buttonConfirmChoice.findNavController().navigate(action)
        }
    }
}