package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.Categorie
import rs.raf.vezbe11.databinding.LayoutItemFilterBinding
import rs.raf.vezbe11.presentation.view.recycler.diff.CategorieDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.CategorieFilterViewHolder

class CategorieFilterAdapter : ListAdapter<Categorie, CategorieFilterViewHolder>(CategorieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorieFilterViewHolder {
        val itemBinding = LayoutItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategorieFilterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CategorieFilterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}