package rs.raf.vezbe11.data.models

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class IngredientResponse(
    @Json(name = "idIngredient") val id: String,
    @Json(name = "strIngredient") val name: String,
    @Json(name = "strDescription") val description: String?
)
