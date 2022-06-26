package com.example.myfridge

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.adapter.FoodItemAdapter
import com.example.myfridge.data.TestDataset
import com.example.myfridge.databinding.FragmentFoodListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FoodListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class FoodListFragment : Fragment() {

    private var _mainContext : Context? = null
    private val mainContext get() = _mainContext!!

    val action = FoodListFragmentDirections.actionFoodListFragmentToModifyFoodFragment()

    // null perché non possiamo inflazionare il layout fino a quando non viene chiamata
    // onCreateView()
    private var _binding : FragmentFoodListBinding? = null

    // proprietà che otterrà il valore di ritornato da _binding una volta che è inizializzato
    // marcato con !! indica una promessa al compilatore che accederò a questa
    // variabile soltanto una volta che è stata inizializzata
    // per questo poi l'assegno a binding, in modo tale che non devo riempire il mio codice con "?"
    // inoltre get() indica che questa proprietà è soltanto get-only
    private val binding get() = _binding!!

    // Loads test dataset
    val testDataset = TestDataset().loadTestFoodItems()

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _mainContext = context
    }

    override fun onDetach() {
        super.onDetach()
        _mainContext = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView

        // assegnamo l'adapter
        recyclerView.adapter = FoodItemAdapter(mainContext, testDataset)

        binding.buttonTest.setOnClickListener{
            binding.buttonTest.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}