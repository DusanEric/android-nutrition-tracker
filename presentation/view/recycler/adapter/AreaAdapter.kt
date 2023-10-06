package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.Area
import rs.raf.vezbe11.databinding.LayoutItemFilterBinding
import rs.raf.vezbe11.presentation.view.recycler.diff.AreaDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.AreaViewHolder

class AreaAdapter : ListAdapter<Area, AreaViewHolder>(AreaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val itemBinding = LayoutItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AreaViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}