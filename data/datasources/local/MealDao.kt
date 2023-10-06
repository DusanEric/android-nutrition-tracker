package rs.raf.vezbe11.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.vezbe11.data.models.CategorieEntity
import rs.raf.vezbe11.data.models.MealEntity

@Dao
abstract class MealDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: MealEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<MealEntity>): Completable

    @Query("SELECT * FROM meals")
    abstract fun getAll(): Observable<List<MealEntity>>

    @Query("DELETE FROM meals")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<MealEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM meals WHERE name LIKE :name || '%'")
    abstract fun getByName(name: String): Observable<List<MealEntity>>

    @Query("SELECT * FROM meals WHERE id LIKE :id || '%'  LIMIT 1")
    abstract fun getById(id: String): Observable<List<MealEntity>>
}