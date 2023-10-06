package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.Ingredient
import rs.raf.vezbe11.data.models.Resource

interface IngredientRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Ingredient>>
    fun getAllByName(name: String): Observable<List<Ingredient>>
    fun insert(ingredient: Ingredient): Completable
}