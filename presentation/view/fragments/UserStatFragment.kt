package rs.raf.vezbe11.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.SavedMeal
import rs.raf.vezbe11.databinding.FragmentListBinding
import rs.raf.vezbe11.databinding.FragmentUserStatBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.adapter.CategorieAdapter
import rs.raf.vezbe11.presentation.view.recycler.adapter.MealAdapter
import rs.raf.vezbe11.presentation.view.recycler.adapter.PlanAdapter
import rs.raf.vezbe11.presentation.view.recycler.adapter.SavedMealAdapter
import rs.raf.vezbe11.presentation.view.states.CategoriesState
import rs.raf.vezbe11.presentation.view.states.MealsState
import rs.raf.vezbe11.presentation.view.states.SavedMealsState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import timber.log.Timber

class UserStatFragment : Fragment(R.layout.fragment_user_stat) {

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private var _binding: FragmentUserStatBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: PlanAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserStatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.listPlanRv.layoutManager = LinearLayoutManager(context)
        adapter = PlanAdapter()
        binding.listPlanRv.adapter = adapter
    }

    private fun initListeners() {
        binding.prikaziButton.setOnClickListener {
            val spinner2 = binding.spinner2
            val filter2 = spinner2.selectedItem.toString()

            val spinner1 = binding.spinner1
            val filter1 = spinner1.selectedItem.toString()

            if (filter2 == "Name" && filter1 == "Top"){
                mainViewModel.getMostSavedMealsByName()
            }else if( filter2 == "Name" && filter1 == "Lowest"){
                mainViewModel.getLowestSavedMealsByName()
            }
        }
    }

    private fun initObservers() {
        mainViewModel.savedMealsState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        val spinner2 = binding.spinner2
        val filter = spinner2.selectedItem.toString()

        Timber.e("SPINNER:" + filter)

        if (filter == "Name"){
            Timber.e("USAO U FILTER")
            mainViewModel.getMostSavedMealsByName()
        }
    }

    private fun renderState(state: SavedMealsState) {
        when (state) {
            is SavedMealsState.Success -> {
                showLoadingState(false)
//                val mealsList = convertSavedMealsToMeals(state.savedMeals)
                adapter.submitList(state.savedMeals)

                Timber.d("Meals list size: ${state.savedMeals.size}")
            }
            is SavedMealsState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is SavedMealsState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is SavedMealsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun convertSavedMealsToMeals(savedMeals: List<SavedMeal>): List<Meal> {
        return savedMeals.map { savedMeal ->
            Meal(
                id = savedMeal.id,
                name = savedMeal.name,
                categorie = savedMeal.category,
                area = "",
                instructions = savedMeal.instructions,
                urlImage = savedMeal.urlImg,
                tags = "",
                urlYT = savedMeal.urlYT,
                ingredients = savedMeal.ingredients,
                measures = savedMeal.measures
            )
        }
    }


    private fun showLoadingState(loading: Boolean) {
        binding.spinner1.isVisible = !loading
        binding.spinner2.isVisible = !loading
        binding.listPlanRv.isVisible = !loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}