package rs.raf.vezbe11.presentation.view.recycler.viewholder

import android.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Categorie
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.databinding.LayoutItemCategorieBinding

class CategorieViewHolder (private val itemBinding: LayoutItemCategorieBinding) : RecyclerView.ViewHolder(itemBinding.root) {



    fun bind(categorie: Categorie,clickListener: (String) -> Unit) {
        itemBinding.nameTv.text = categorie.name

        val categoryImageView = itemBinding.categorieImageView

        Glide.with(itemView)
            .load(categorie.url)
            .centerCrop()
            .into(categoryImageView)

        itemView.setOnClickListener {
            clickListener(categorie.name)
        }
        itemBinding.moreButton.setOnClickListener {
            showDescriptionDialog(categorie.description)
        }
    }

    private fun showDescriptionDialog(description: String) {
        val dialog = AlertDialog.Builder(itemView.context)
            .setTitle("Category Description")
            .setMessage(description)
            .setPositiveButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }
}