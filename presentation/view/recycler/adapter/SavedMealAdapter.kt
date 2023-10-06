package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.SavedMeal
import rs.raf.vezbe11.databinding.LayoutItemSavedMealBinding
import rs.raf.vezbe11.presentation.view.recycler.diff.MealDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.SavedMealViewHolder

class SavedMealAdapter : ListAdapter<Meal, SavedMealViewHolder>(
    MealDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedMealViewHolder {
        val binding = LayoutItemSavedMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedMealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedMealViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

}