package rs.raf.vezbe11.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.vezbe11.data.models.Categorie
import rs.raf.vezbe11.data.models.Movie

class CategorieDiffCallback : DiffUtil.ItemCallback<Categorie>() {

    override fun areItemsTheSame(oldItem: Categorie, newItem: Categorie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Categorie, newItem: Categorie): Boolean {
        return oldItem.name == newItem.name
    }


}