package rs.raf.vezbe11.presentation.view.fragments

import android.annotation.SuppressLint
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
import rs.raf.vezbe11.databinding.FragmentListBinding
import rs.raf.vezbe11.databinding.FragmentListMealsBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.adapter.CategorieAdapter
import rs.raf.vezbe11.presentation.view.recycler.adapter.MealAdapter
import rs.raf.vezbe11.presentation.view.states.CategoriesState
import rs.raf.vezbe11.presentation.view.states.MealsState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import timber.log.Timber

class ListMealsFragment : Fragment(R.layout.fragment_list_meals) {

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private var _binding: FragmentListMealsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: MealAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListMealsBinding.inflate(inflater, container, false)
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
        binding.listMealsRv.layoutManager = LinearLayoutManager(context)
        adapter = MealAdapter{ mealId ->
            val fragment = ListDetailMealFragment.newInstance(mealId)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit()
        }
        binding.listMealsRv.adapter = adapter
    }

    private fun initListeners() {
        binding.inputMealEt.doAfterTextChanged {
            val filter = it.toString()
            mainViewModel.getMealsByName(filter)
        }
    }

    private fun initObservers() {
        mainViewModel.mealsState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        mainViewModel.getAllMeals()

        val categoryId = arguments?.getString("categoryId")
        categoryId?.let {
            mainViewModel.fetchAllMeals(it)
        }

//        mainViewModel.fetchAllMeals("Beef")
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
        binding.inputMealEt.isVisible = !loading
        binding.listMealsRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    companion object {
        fun newInstance(categoryId: String): ListMealsFragment {
            val fragment = ListMealsFragment()
            val bundle = Bundle().apply {
                putString("categoryId", categoryId)
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