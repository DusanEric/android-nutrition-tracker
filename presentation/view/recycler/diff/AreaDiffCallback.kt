package rs.raf.vezbe11.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.vezbe11.data.models.Area

class AreaDiffCallback : DiffUtil.ItemCallback<Area>() {

    override fun areItemsTheSame(oldItem: Area, newItem: Area): Boolean {
        return oldItem.area == newItem.area
    }

    override fun areContentsTheSame(oldItem: Area, newItem: Area): Boolean {
        return oldItem.area == newItem.area
    }


}