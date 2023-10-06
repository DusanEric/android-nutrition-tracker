package rs.raf.vezbe11.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategorieEntity (
    @PrimaryKey
    val id: String,
    val name: String,
    val url: String,
    val description: String
)
