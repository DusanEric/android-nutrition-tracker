package rs.raf.vezbe11.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.databinding.LayoutItemMealBinding

class MealViewHolder (private val itemBinding: LayoutItemMealBinding) : RecyclerView.ViewHolder(itemBinding.root) {



    fun bind(meal: Meal,clickListener: (String) -> Unit) {
        itemBinding.titleTv.text = meal.name

        val categoryImageView = itemBinding.mealImageView

        Glide.with(itemView)
            .load(meal.urlImage)
            .centerCrop()
            .into(categoryImageView)

        itemView.setOnClickListener {
            clickListener(meal.id)
        }
    }
}