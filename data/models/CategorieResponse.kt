package rs.raf.vezbe11.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategorieResponse (
    @Json(name = "idCategory") val id: String,
    @Json(name = "strCategory") val name: String,
    @Json(name = "strCategoryThumb") val url: String,
    @Json(name = "strCategoryDescription") val description: String
    )