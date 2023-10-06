package rs.raf.vezbe11.presentation.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.SavedMeal
import rs.raf.vezbe11.databinding.FragmentDetailMealBinding
import rs.raf.vezbe11.databinding.FragmentSavedMealBinding
import rs.raf.vezbe11.databinding.LayoutItemSavedMealBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.adapter.DetailMealAdapter
import rs.raf.vezbe11.presentation.view.recycler.adapter.SavedMealAdapter
import rs.raf.vezbe11.presentation.view.states.MealsState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import timber.log.Timber
import java.time.LocalDate
import java.util.*

class SavedMealFragment : Fragment(R.layout.fragment_saved_meal) {

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private var _binding: FragmentSavedMealBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: SavedMealAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedMealBinding.inflate(inflater, container, false)
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
        binding.lisRvSavedMeal.layoutManager = LinearLayoutManager(context)
        adapter = SavedMealAdapter()
        binding.lisRvSavedMeal.adapter = adapter
    }

    @SuppressLint("CheckResult")
    private fun initListeners() {
        binding.saveButton.setOnClickListener{
            Timber.e("Klik sacuvaj")

            val mealId = arguments?.getString("mealId")

            mealId?.let { id ->
                mainViewModel.getMealById(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { meals ->
                            val meal = meals.find { it.id == id }
                            if (meal != null) {
                                val year = binding.datePicker.year
                                val month = binding.datePicker.month
                                val day = binding.datePicker.dayOfMonth

                                val calendar = Calendar.getInstance()
                                calendar.set(Calendar.YEAR, year)
                                calendar.set(Calendar.MONTH, month)
                                calendar.set(Calendar.DAY_OF_MONTH, day)

                                val selectedDate = calendar.time

                                val spinner = binding.mealCategorySpinner
                                val categoryTime = spinner.selectedItem.toString()

                                val temp: UUID = UUID.randomUUID()

                                val savedMeal = SavedMeal(
                                    id = java.lang.Long.toHexString(temp.mostSignificantBits)
                                            + java.lang.Long.toHexString(temp.leastSignificantBits),
                                    name = meal.name,
                                    urlImg = "meal.urlImage",
                                    instructions = meal.instructions,
                                    urlYT = "meal.urlYT",
                                    ingredients = meal.ingredients,
                                    measures = listOf("meal.measures"),
                                    category = meal.categorie,
                                    date = selectedDate,
                                    categoryTime = categoryTime
                                )

                                mainViewModel.addSavedMeal(savedMeal)
                            } else {
                                Timber.e("No meal with selected ID")
                            }
                        },
                        { error ->
                            Timber.e(error)
                        }
                    )
            }
        }
    }

    private fun initObservers() {
        mainViewModel.mealsState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        mainViewModel.getMeal()

        val mealId = arguments?.getString("mealId")
        mealId?.let {
            mainViewModel.fetchMeal(it)
        }

    }

    private fun renderState(state: MealsState) {
        when (state) {
            is MealsState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.meals)
            }
            is MealsState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is MealsState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is MealsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
//        binding.listRv.isVisible = !loading
    }

    companion object {
        fun newInstance(mealId: String): SavedMealFragment {
            val fragment = SavedMealFragment()
            val bundle = Bundle().apply {
                putString("mealId", mealId)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}