package rs.raf.vezbe11.presentation.view.recycler.viewholder

import android.text.util.Linkify
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.databinding.LayoutItemDetailMealBinding

class DetailMealViewHolder (private val itemBinding: LayoutItemDetailMealBinding) : RecyclerView.ViewHolder(itemBinding.root) {


    fun bind(meal: Meal,clickListener: (String) -> Unit) {
        itemBinding.titleTv.text = meal.name

        itemBinding.miniTitle1Tv.text = meal.categorie
        itemBinding.miniTitle2Tv.text = meal.area

        val mealImageView = itemBinding.imageView

        Glide.with(itemView)
            .load(meal.urlImage)
            .centerCrop()
            .into(mealImageView)

        itemBinding.textView1.text = meal.instructions
        itemBinding.textView2.text = meal.measures.toString()

        val myTextView = itemBinding.linkTv
        myTextView.text = meal.urlYT
        Linkify.addLinks(myTextView, Linkify.WEB_URLS)


        itemBinding.sacuvajButton.setOnClickListener{
            clickListener(meal.id)
        }

//        itemView.setOnClickListener {
//            clickListener(meal.id)
//        }

    }
}