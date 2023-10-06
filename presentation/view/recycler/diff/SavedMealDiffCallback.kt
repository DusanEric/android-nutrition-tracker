package rs.raf.vezbe11.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.SavedMeal

class SavedMealDiffCallback : DiffUtil.ItemCallback<SavedMeal>() {

    override fun areItemsTheSame(oldItem: SavedMeal, newItem: SavedMeal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SavedMeal, newItem: SavedMeal): Boolean {
        return oldItem.name == newItem.name
    }


}