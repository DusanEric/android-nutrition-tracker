package rs.raf.vezbe11.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AllMealsResponse(
    @Json(name = "meals")val allMeals: List<MealResponse>
)
