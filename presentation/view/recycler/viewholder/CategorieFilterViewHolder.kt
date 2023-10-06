package rs.raf.vezbe11.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.vezbe11.data.models.Categorie
import rs.raf.vezbe11.databinding.LayoutItemCategorieBinding
import rs.raf.vezbe11.databinding.LayoutItemFilterBinding

class CategorieFilterViewHolder (private val itemBinding: LayoutItemFilterBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(categorie: Categorie) {
        itemBinding.titleTv.text = categorie.name

    }
}