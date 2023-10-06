package rs.raf.vezbe11.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.vezbe11.data.models.Area
import rs.raf.vezbe11.databinding.LayoutItemFilterBinding

class AreaViewHolder (private val itemBinding: LayoutItemFilterBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(area: Area) {
        itemBinding.titleTv.text = area.area

    }
}