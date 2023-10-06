package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.databinding.LayoutItemMealBinding
import rs.raf.vezbe11.presentation.view.recycler.diff.MealDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.MealViewHolder

class MealAdapter(private val clickListener: (String) -> Unit) : ListAdapter<Meal, MealViewHolder>(MealDiffCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
            val binding = LayoutItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MealViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
            val currentItem = getItem(position)
            holder.bind(currentItem,clickListener)
        }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
//        val itemBinding = LayoutItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MealViewHolder(itemBinding)
//    }
//
//    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
//        holder.bind(getItem(position))
//    }

}