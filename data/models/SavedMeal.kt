package rs.raf.vezbe11.data.models

import java.util.Date

data class SavedMeal(
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
