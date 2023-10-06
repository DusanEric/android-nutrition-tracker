package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.Resource

interface MealRepository {

    fun fetchAll(category: String): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Meal>>
    fun getAllByName(name: String): Observable<List<Meal>>
    fun getById(id : String): Observable<List<Meal>>
    fun insert(meal: Meal): Completable
}