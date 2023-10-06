package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.Ingredient
import rs.raf.vezbe11.databinding.LayoutItemFilterBinding
import rs.raf.vezbe11.presentation.view.recycler.diff.IngredientDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.IngredientViewHolder

class IngredientAdapter : ListAdapter<Ingredient, IngredientViewHolder>(IngredientDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val itemBinding = LayoutItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}