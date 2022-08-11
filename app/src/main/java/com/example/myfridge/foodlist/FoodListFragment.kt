package com.example.myfridge.foodlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.databinding.FragmentFoodListBinding

class FoodListFragment : Fragment() {

    private val action = FoodListFragmentDirections.actionFoodListFragmentToModifyFoodFragment()

    // null perché non possiamo inflazionare il layout fino a quando non viene chiamata
    // onCreateView()
    private var _binding: FragmentFoodListBinding? = null

    // proprietà che otterrà il valore di ritornato da _binding una volta che è inizializzato
    // marcato con !! indica una promessa al compilatore che accederò a questa
    // variabile soltanto una volta che è stata inizializzata
    // per questo poi l'assegno a binding, in modo tale che non devo riempire il mio codice con "?"
    // inoltre get() indica che questa proprietà è soltanto get-only
    private val binding get() = _binding!!

    // Loads test dataset
    //private var foodDataset = FoodDataset().loadTestFoodItems()

    //private var foodListViewModel by viewModels<FoodListViewModel > { defaultViewModelProviderFactory() }
    private var foodListViewModel: FoodListViewModel = FoodListViewModel()

    // RecyclerView
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodListBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView

        recyclerView.adapter = FoodItemAdapter(foodListViewModel.foodList)

        // Creiamo un observer che quando attivato rilancia la funziona adapter e ricrea la lista
        val foodObserver = Observer<String> {
            recyclerView.adapter = FoodItemAdapter(foodListViewModel.foodList)
        }
        // Diciamo all'observer di controllare cambiamenti alla LiveData status in modo tale che una volta
        // che il ViewModel aggiorna il dato, la recyclerView viene ricostruita
        foodListViewModel.status.observe(viewLifecycleOwner, foodObserver)


        binding.buttonTest.setOnClickListener {
            binding.buttonTest.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
