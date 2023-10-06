package rs.raf.vezbe11.presentation.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.StatisticsEntry
import rs.raf.vezbe11.databinding.FragmentStatisticBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import timber.log.Timber
import java.util.*

class StatisticsFragment : Fragment(R.layout.fragment_statistic) {

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private var _binding: FragmentStatisticBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatisticBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        loadStatisticsData()
    }

    private fun initUi() {
        // Initialize your chart here
        val chart = binding.statisticsChart

        // Configure the chart
        chart.description.text = "Meals Added in Last 7 Days"
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM // X-axis at the bottom
        chart.xAxis.setDrawGridLines(false) // Hide grid lines on X-axis

        // You can further customize the chart as needed, e.g., set colors, legends, etc.

        // Load data and populate the chart
        loadStatisticsData()
    }


    private fun loadStatisticsData() {
        val currentDate = Calendar.getInstance()
        val statisticsData = mutableListOf<StatisticsEntry>()

        for (i in 1..7) {
            val date = currentDate.time // Get the date for the current day
            val mealsAdded = mainViewModel.getMealsAddedForDate(date)

            // Create a StatisticsEntry object with the date and mealsAdded count
            val entry = StatisticsEntry(date, mealsAdded)
            statisticsData.add(entry)

            // Move to the previous day for the next iteration
            currentDate.add(Calendar.DAY_OF_MONTH, -1)
        }

        // Now that you have the data, update the chart
        updateChartWithData(statisticsData)
    }


    private fun updateChartWithData(statisticsData: List<StatisticsEntry>) {
        // Create an ArrayList of Entry objects to hold your data points
        val entries = ArrayList<Entry>()

        // Loop through the statisticsData and add each data point to the entries list
        for ((index, entry) in statisticsData.withIndex()) {
            // You can't directly convert an Observable<Int> to a Float
            // Instead, you need to subscribe to it and handle the emitted value
            mainViewModel.getMealsAddedForDate(entry.date)
                ?.subscribe(
                    { mealsAdded ->
                        // Add the data point with the X-axis as the index (0, 1, 2, ...) and Y-axis as mealsAdded
                        entries.add(Entry(index.toFloat(), mealsAdded.toFloat()))

                        // If this is the last entry, update the chart
                        if (index == statisticsData.size - 1) {
                            updateChart(entries)
                        }
                    },
                    { error ->
                        Timber.e("GRESKA u chartu")
                    }
                )
        }
    }

    private fun updateChart(entries: ArrayList<Entry>) {
        // Create a LineDataSet from the entries
        val dataSet = LineDataSet(entries, "Meals Added")

        // Customize the appearance of the dataset
        dataSet.color = Color.BLUE // Line color
        dataSet.valueTextColor = Color.BLACK // Value text color

        // Create a LineData object from the dataSet
        val lineData = LineData(dataSet)

        // Get a reference to your LineChart
        val chart = binding.statisticsChart

        // Set the data to your LineChart
        chart.data = lineData

        // Refresh the chart
        chart.invalidate()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
