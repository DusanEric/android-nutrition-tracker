package rs.raf.vezbe11.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.SavedMealEntity
import java.util.*

@Dao
abstract class SavedMealDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: SavedMealEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<SavedMealEntity>): Completable

    @Query("SELECT * FROM savedMeals")
    abstract fun getAll(): Observable<List<SavedMealEntity>>

    @Query("DELETE FROM savedMeals")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<SavedMealEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM savedMeals WHERE name LIKE :name || '%'")
    abstract fun getByName(name: String): Observable<List<SavedMealEntity>>

    @Query("SELECT * FROM savedMeals WHERE date BETWEEN :startDate AND :endDate")
    abstract fun getMealsAddedBetweenDates(startDate: Date, endDate: Date): Observable<List<SavedMealEntity>>

    @Query("SELECT *, COUNT(*) as mealCount FROM savedMeals GROUP BY name ORDER BY mealCount DESC")
    abstract fun getMostSavedMealsByName(): Observable<List<SavedMealEntity>>

    @Query("SELECT *, COUNT(*) as mealCount FROM savedMeals GROUP BY name ORDER BY mealCount ASC")
    abstract fun getLowestSavedMealsByName(): Observable<List<SavedMealEntity>>


}