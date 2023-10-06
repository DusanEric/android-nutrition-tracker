package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.SavedMealDao
import rs.raf.vezbe11.data.models.SavedMeal
import rs.raf.vezbe11.data.models.SavedMealEntity
import java.util.*

class SavedMealRepositoryImpl(
    private val localDataSource: SavedMealDao,
): SavedMealRepository {
    override fun getAll(): Observable<List<SavedMeal>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    SavedMeal(it.id, it.name,it.urlImg,it.instructions,it.urlYT,it.ingredients,
                    it.measures,it.category,it.date,it.categoryTime)
                }
            }
    }

    override fun insert(savedMeal: SavedMeal): Completable {
        val savedMealEntity = SavedMealEntity(savedMeal.id, savedMeal.name, savedMeal.urlImg,savedMeal.instructions,savedMeal.urlYT,savedMeal.ingredients,
            savedMeal.measures,savedMeal.category,savedMeal.date,savedMeal.categoryTime)
        return localDataSource
            .insert(savedMealEntity)
    }

    override fun getMealsAddedBetweenDates(
        startDate: Date,
        endDate: Date
    ): Observable<List<SavedMeal>> {
        return localDataSource
            .getMealsAddedBetweenDates(startDate, endDate)
            .map {
                it.map {
                    SavedMeal(it.id, it.name, it.urlImg, it.instructions, it.urlYT, it.ingredients, it.measures, it.category, it.date, it.categoryTime)
                }
            }
    }

    override fun getMostSavedMealsByName(): Observable<List<SavedMeal>> {
        return localDataSource
            .getMostSavedMealsByName()
            .map { savedMealEntities ->
                savedMealEntities.map { entity ->
                    SavedMeal(
                        entity.id,
                        entity.name,
                        entity.urlImg,
                        entity.instructions,
                        entity.urlYT,
                        entity.ingredients,
                        entity.measures,
                        entity.category,
                        entity.date,
                        entity.categoryTime
                    )
                }
            }
    }

    override fun getLowestSavedMealsByName(): Observable<List<SavedMeal>> {
        return localDataSource
            .getLowestSavedMealsByName()
            .map { savedMealEntities ->
                savedMealEntities.map { entity ->
                    SavedMeal(
                        entity.id,
                        entity.name,
                        entity.urlImg,
                        entity.instructions,
                        entity.urlYT,
                        entity.ingredients,
                        entity.measures,
                        entity.category,
                        entity.date,
                        entity.categoryTime
                    )
                }
            }
    }

}