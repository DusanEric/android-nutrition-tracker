package rs.raf.vezbe11.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.vezbe11.data.models.AllMealsResponse

interface DetailMealService {
    @GET("lookup.php")
    fun getAll(@Query("i") id: String): Observable<AllMealsResponse>
}