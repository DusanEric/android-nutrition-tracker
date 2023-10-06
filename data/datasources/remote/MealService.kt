package rs.raf.vezbe11.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.vezbe11.data.models.AllMealsResponse

interface MealService {
    @GET("filter.php")
    fun getAll(@Query("c") category: String): Observable<AllMealsResponse>
}