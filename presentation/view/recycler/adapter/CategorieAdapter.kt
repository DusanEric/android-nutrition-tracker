package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.Categorie
import rs.raf.vezbe11.databinding.LayoutItemCategorieBinding
import rs.raf.vezbe11.presentation.view.recycler.diff.CategorieDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.CategorieViewHolder

class CategorieAdapter(private val clickListener: (String) -> Unit) : ListAdapter<Categorie, CategorieViewHolder>(CategorieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorieViewHolder {
        val itemBinding = LayoutItemCategorieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategorieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CategorieViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener)
    }

}