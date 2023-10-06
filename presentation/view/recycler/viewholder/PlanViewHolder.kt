package rs.raf.vezbe11.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.SavedMeal
import rs.raf.vezbe11.databinding.LayoutItemPlanBinding

class PlanViewHolder (private val itemBinding: LayoutItemPlanBinding) : RecyclerView.ViewHolder(itemBinding.root) {


    fun bind(savedMeal: SavedMeal) {
        itemBinding.savedMealNameTv.text = savedMeal.name

//        val categoryImageView = itemBinding.savedMealImageView
//
//        Glide.with(itemView)
//            .load(meal.urlImg)
//            .centerCrop()
//            .into(categoryImageView)
    }
}