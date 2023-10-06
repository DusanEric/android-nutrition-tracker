package rs.raf.vezbe11.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "savedMeals")
data class SavedMealEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val urlImg : String,
    val instructions: String?,
    val urlYT : String,
    val ingredients: List<String>?,
    val measures: List<String>?,
    val category: String?,
    val date: Date?,
    val categoryTime: String
)
