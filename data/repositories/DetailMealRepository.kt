package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.Resource

interface DetailMealRepository {
    fun fetchAll(category: String): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Meal>>
    fun getAllByName(name: String): Observable<List<Meal>>
    fun insert(meal: Meal): Completable
}