package rs.raf.vezbe11.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.CategorieEntity

@Dao
abstract class CategorieDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: CategorieEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<CategorieEntity>): Completable

    @Query("SELECT * FROM categories")
    abstract fun getAll(): Observable<List<CategorieEntity>>

    @Query("DELETE FROM categories")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<CategorieEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM categories WHERE name LIKE :name || '%'")
    abstract fun getByName(name: String): Observable<List<CategorieEntity>>
}