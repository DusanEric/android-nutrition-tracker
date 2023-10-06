package rs.raf.vezbe11.data.models

data class Meal(
    val id: String,
    val name: String,
    val categorie: String?,
    val area: String?,
    val instructions: String?,
    val urlImage : String?,
    val tags: String?,
    val urlYT: String?,
    val ingredients: List<String>?,
    val measures: List<String>?
)
