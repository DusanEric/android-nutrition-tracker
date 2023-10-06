package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.FragmentListFilterBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.adapter.AreaAdapter
import rs.raf.vezbe11.presentation.view.recycler.adapter.CategorieAdapter
import rs.raf.vezbe11.presentation.view.recycler.adapter.CategorieFilterAdapter
import rs.raf.vezbe11.presentation.view.recycler.adapter.IngredientAdapter
import rs.raf.vezbe11.presentation.view.states.AreasState
import rs.raf.vezbe11.presentation.view.states.CategoriesState
import rs.raf.vezbe11.presentation.view.states.IngredientsState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import timber.log.Timber

class ListFilterFragment : Fragment(R.layout.fragment_list_filter) {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private var _binding: FragmentListFilterBinding? = null
    private val binding get() = _binding!!

    // Declare a generic RecyclerView.Adapter<RecyclerView.ViewHolder>
    private lateinit var adapter1: AreaAdapter
    private var areaFlag: Boolean = true
    private lateinit var adapter2: CategorieFilterAdapter
    private var categorieFlag: Boolean = true
    private lateinit var adapter3: IngredientAdapter
    private var ingredientFlag: Boolean = true
    // NE RADI ZA INGREDIENTE

    // Declare a variable to hold the currently selected adapter type
//    private var selectedAdapterType: AdapterType = AdapterType.AREA // Default to AreaAdapter
//
//    enum class AdapterType {
//        AREA, CATEGORIE, INGREDIENT
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initListeners()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
    }

    private fun initRecycler() {
        binding.listRv.layoutManager = LinearLayoutManager(context)
        // Initialize the default adapter (AreaAdapter in this case)
        adapter1 = AreaAdapter()
        adapter2 = CategorieFilterAdapter()
        adapter3 = IngredientAdapter()
//        binding.filterRadioGroup.setOnCheckedChangeListener{_, chekedId ->
//            if(chekedId.equals("Area")){
//                binding.listRv.adapter = adapter1
//            }else if(chekedId.equals("Ingredient")){
//                areaFlag = false
//                ingredientFlag = true
//                binding.listRv.adapter = adapter3
//            }
//        }
//        binding.filterRadioGroup.setOnCheckedChangeListener { _, checkedId ->
//            when (checkedId) {
//                R.id.areasRadioButton -> {
//                    // Set the adapter to AreaAdapter
//                    binding.listRv.adapter = adapter1
//                }
//                R.id.ingredientsRadioButton -> {
//                    areaFlag = false
//                    ingredientFlag = true
//                    binding.listRv.adapter = adapter3
//                }
//                R.id.categoriesRadioButton -> {
//                    areaFlag = false
//                    ingredientFlag = false
//                    categorieFlag = true
//                    binding.listRv.adapter = adapter2
//                }
//                else -> {
//                    // Handle other cases or do nothing if needed
//                }
//            }
//        }
//        binding.listRv.adapter = adapter1
    }

    private fun initListeners() {

    }

    private fun initObservers() {

        binding.filterRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.areasRadioButton -> {
                    areaFlag= true
                    ingredientFlag = false
                    categorieFlag = false
                    // Set the adapter to AreaAdapter
                    binding.listRv.adapter = adapter1
                }
                R.id.ingredientsRadioButton -> {
                    areaFlag = false
                    ingredientFlag = true
                    categorieFlag = false
                    binding.listRv.adapter = adapter3
                }
                R.id.categoriesRadioButton -> {
                    areaFlag = false
                    ingredientFlag = false
                    categorieFlag = true
                    binding.listRv.adapter = adapter2
                }
                else -> {
                    // Handle other cases or do nothing if needed
                }
            }
        }

        if (areaFlag) {
            mainViewModel.areasState.observe(viewLifecycleOwner, Observer {
                Timber.e(it.toString())
                renderAreaState(it)
            })

            mainViewModel.getAllAreas()

            mainViewModel.fetchAllAreas()
        }
        if(ingredientFlag){
            mainViewModel.ingredientsState.observe(viewLifecycleOwner, Observer {
                Timber.e(it.toString())
                renderIngredientState(it)
            })

            mainViewModel.getAllIngredinets()

            mainViewModel.fetchAllIngredinets()
        }
        if (categorieFlag){
            mainViewModel.categoriesState.observe(viewLifecycleOwner, Observer {
                Timber.e(it.toString())
                renderCategorieState(it)
            })

            mainViewModel.getAllCategories()

            mainViewModel.fetchAllCategories()
        }
    }

    private fun renderAreaState(state: AreasState) {
        when (state) {
            is AreasState.Success -> {
                showLoadingState(false)
                adapter1.submitList(state.areas)
            }
            is AreasState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is AreasState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is AreasState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun renderIngredientState(state: IngredientsState) {
        when (state) {
            is IngredientsState.Success -> {
                showLoadingState(false)
                adapter3.submitList(state.ingredients)
            }
            is IngredientsState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is IngredientsState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is IngredientsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun renderCategorieState(state: CategoriesState) {
        when (state) {
            is CategoriesState.Success -> {
                showLoadingState(false)
                adapter2.submitList(state.categories)
            }
            is CategoriesState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is CategoriesState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is CategoriesState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.filterRadioGroup.isVisible = !loading
        binding.listRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}