package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.IngredientDao
import rs.raf.vezbe11.data.datasources.remote.IngredientService
import rs.raf.vezbe11.data.models.*
import timber.log.Timber

class IngredientRepositoryImpl(
    private val localDataSource: IngredientDao,
    private val remoteDataSource: IngredientService
    ) : IngredientRepository {


    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.allIngredients.map {
                    IngredientEntity(
                        it.id,
                        it.name,
                        it.description
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<Ingredient>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Ingredient(it.id, it.name,it.description)
                }
            }
    }

    override fun getAllByName(name: String): Observable<List<Ingredient>> {
        return localDataSource
            .getByName(name)
            .map {
                it.map {
                    Ingredient(it.id, it.name,it.description)
                }
            }
    }

    override fun insert(ingredient: Ingredient): Completable {
        val ingredientEntity = IngredientEntity(ingredient.id, ingredient.name, ingredient.description)
        return localDataSource
            .insert(ingredientEntity)
    }
}