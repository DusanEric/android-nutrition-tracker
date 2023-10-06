package rs.raf.vezbe11.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String?
)
