package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.SavedMeal
import java.util.*

interface SavedMealRepository {

    fun getAll(): Observable<List<SavedMeal>>
    fun insert(savedMeal: SavedMeal): Completable
    fun getMealsAddedBetweenDates(startDate: Date, endDate: Date): Observable<List<SavedMeal>>
    fun getMostSavedMealsByName(): Observable<List<SavedMeal>>
    fun getLowestSavedMealsByName(): Observable<List<SavedMeal>>
}