package rs.raf.vezbe11.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.vezbe11.data.models.Ingredient
import rs.raf.vezbe11.databinding.LayoutItemFilterBinding

class IngredientViewHolder (private val itemBinding: LayoutItemFilterBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(ingredient: Ingredient) {
        itemBinding.titleTv.text = ingredient.name

    }
}