package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.databinding.LayoutItemDetailMealBinding
import rs.raf.vezbe11.presentation.view.recycler.diff.MealDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.DetailMealViewHolder

class DetailMealAdapter(private val clickListener: (String) -> Unit) : ListAdapter<Meal, DetailMealViewHolder>(MealDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailMealViewHolder {
        val binding = LayoutItemDetailMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailMealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailMealViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem,clickListener)
    }
}