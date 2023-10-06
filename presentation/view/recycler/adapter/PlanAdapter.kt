package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.SavedMeal
import rs.raf.vezbe11.databinding.LayoutItemPlanBinding
import rs.raf.vezbe11.presentation.view.recycler.diff.SavedMealDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.PlanViewHolder

class PlanAdapter : ListAdapter<SavedMeal, PlanViewHolder>(
    SavedMealDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val binding = LayoutItemPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

}