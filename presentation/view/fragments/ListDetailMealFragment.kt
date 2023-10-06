package rs.raf.vezbe11.presentation.view.fragments

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
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.FragmentDetailMealBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.adapter.DetailMealAdapter
import rs.raf.vezbe11.presentation.view.recycler.adapter.SavedMealAdapter
import rs.raf.vezbe11.presentation.view.states.MealsState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import timber.log.Timber

class ListDetailMealFragment : Fragment(R.layout.fragment_detail_meal) {

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private var _binding: FragmentDetailMealBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: DetailMealAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailMealBinding.inflate(inflater, container, false)
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
        val scrollView = ScrollView(requireContext())

        val recyclerView = RecyclerView(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = DetailMealAdapter{mealId ->
        val fragment = SavedMealFragment.newInstance(mealId)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment)
            .addToBackStack(null)
            .commit()}
        recyclerView.adapter = adapter

        scrollView.addView(recyclerView)

//        binding.frameDetailMeal.removeAllViews()
        binding.frameDetailMeal.addView(scrollView)
//        binding.listRv.adapter = adapter
    }

    private fun initListeners() {

    }

    private fun initObservers() {
        mainViewModel.mealsState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        mainViewModel.getMeal()

        val categoryId = arguments?.getString("mealId")
        categoryId?.let {
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
//        binding.sacuvajButton.isVisible = loading
    }

    companion object {
        fun newInstance(mealId: String): ListDetailMealFragment {
            val fragment = ListDetailMealFragment()
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