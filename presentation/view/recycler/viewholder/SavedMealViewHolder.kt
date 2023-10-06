package rs.raf.vezbe11.presentation.view.recycler.viewholder

import android.app.AlertDialog
import android.content.Intent
import android.provider.MediaStore
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.databinding.LayoutItemSavedMealBinding
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import timber.log.Timber

class SavedMealViewHolder (private val itemBinding: LayoutItemSavedMealBinding) : RecyclerView.ViewHolder(itemBinding.root) {


//    fun bind(meal: Meal) {
//        itemBinding.dishNameTextView.text = meal.name
//
//        val categoryImageView = itemBinding.dishImageView
//
//        Glide.with(itemView)
//            .load(meal.urlImage)
//            .centerCrop()
//            .into(categoryImageView)
//    }

        init {
            itemBinding.dishImageView.setOnClickListener {
                showCameraConfirmationDialog()
            }
        }

        fun bind(meal: Meal) {
            itemBinding.dishNameTextView.text = meal.name

            val categoryImageView = itemBinding.dishImageView

            Glide.with(itemView)
                .load(meal.urlImage)
                .centerCrop()
                .into(categoryImageView)
        }

        private fun showCameraConfirmationDialog() {
            val builder = AlertDialog.Builder(itemView.context)
            builder.setTitle("Otvaranje kamere")
            builder.setMessage("Da li želite da otvorite kameru telefona?")
            builder.setPositiveButton("Da") { _, _ ->
                openCamera()
            }
            builder.setNegativeButton("Ne") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        private fun openCamera() {
             val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
             if (cameraIntent.resolveActivity(itemView.context.packageManager) != null) {
                 itemView.context.startActivity(cameraIntent)
             }
        }
}