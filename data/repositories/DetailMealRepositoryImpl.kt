package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.MealDao
import rs.raf.vezbe11.data.datasources.remote.DetailMealService
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.Resource
import timber.log.Timber

class DetailMealRepositoryImpl (
    private val localDataSource: MealDao,
    private val remoteDataSource: DetailMealService
) : DetailMealRepository {

    override fun fetchAll(id: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll(id)
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.allMeals.map {
                    val ingredientsList = mutableListOf<String>()

                    // Add each ingredient to the list
                    if (!it.ingredient1.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient1)
                    }
                    if (!it.ingredient2.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient2)
                    }
                    if (!it.ingredient3.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient3)
                    }
                    if (!it.ingredient4.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient4)
                    }
                    if (!it.ingredient5.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient5)
                    }
                    if (!it.ingredient6.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient6)
                    }
                    if (!it.ingredient7.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient7)
                    }
                    if (!it.ingredient8.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient8)
                    }
                    if (!it.ingredient9.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient9)
                    }
                    if (!it.ingredient10.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient10)
                    }
                    if (!it.ingredient11.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient11)
                    }
                    if (!it.ingredient12.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient12)
                    }
                    if (!it.ingredient13.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient13)
                    }
                    if (!it.ingredient14.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient14)
                    }
                    if (!it.ingredient15.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient15)
                    }
                    if (!it.ingredient16.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient16)
                    }
                    if (!it.ingredient17.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient17)
                    }
                    if (!it.ingredient18.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient18)
                    }
                    if (!it.ingredient19.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient19)
                    }
                    if (!it.ingredient20.isNullOrBlank()) {
                        ingredientsList.add(it.ingredient20)
                    }

                    val measuresList = mutableListOf<String>()

                    // Add each ingredient to the list
                    if (!it.measure1.isNullOrBlank()) {
                        ingredientsList.add(it.measure1)
                    }
                    if (!it.measure2.isNullOrBlank()) {
                        ingredientsList.add(it.measure2)
                    }
                    if (!it.measure3.isNullOrBlank()) {
                        ingredientsList.add(it.measure3)
                    }
                    if (!it.measure4.isNullOrBlank()) {
                        ingredientsList.add(it.measure4)
                    }
                    if (!it.measure5.isNullOrBlank()) {
                        ingredientsList.add(it.measure5)
                    }
                    if (!it.measure6.isNullOrBlank()) {
                        ingredientsList.add(it.measure6)
                    }
                    if (!it.measure7.isNullOrBlank()) {
                        ingredientsList.add(it.measure7)
                    }
                    if (!it.measure8.isNullOrBlank()) {
                        ingredientsList.add(it.measure8)
                    }
                    if (!it.measure9.isNullOrBlank()) {
                        ingredientsList.add(it.measure9)
                    }
                    if (!it.measure10.isNullOrBlank()) {
                        ingredientsList.add(it.measure10)
                    }
                    if (!it.measure11.isNullOrBlank()) {
                        ingredientsList.add(it.measure11)
                    }
                    if (!it.measure12.isNullOrBlank()) {
                        ingredientsList.add(it.measure12)
                    }
                    if (!it.measure13.isNullOrBlank()) {
                        ingredientsList.add(it.measure13)
                    }
                    if (!it.measure14.isNullOrBlank()) {
                        ingredientsList.add(it.measure14)
                    }
                    if (!it.measure15.isNullOrBlank()) {
                        ingredientsList.add(it.measure15)
                    }
                    if (!it.measure16.isNullOrBlank()) {
                        ingredientsList.add(it.measure16)
                    }
                    if (!it.measure17.isNullOrBlank()) {
                        ingredientsList.add(it.measure17)
                    }
                    if (!it.measure18.isNullOrBlank()) {
                        ingredientsList.add(it.measure18)
                    }
                    if (!it.measure19.isNullOrBlank()) {
                        ingredientsList.add(it.measure19)
                    }
                    if (!it.measure20.isNullOrBlank()) {
                        ingredientsList.add(it.measure20)
                    }

                    MealEntity(
                        it.id,
                        it.name,
                        it.categorie,
                        it.area,
                        it.instructions,
                        it.urlImage,
                        it.tags,
                        it.urlYT,
                        ingredientsList,
                        measuresList
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<Meal>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Meal(it.id, it.name,it.categorie,it.area, it.instructions,
                        it.urlImage,it.tags, it.urlYT,it.ingredients,it.measures)
                }
            }
    }

    override fun getAllByName(name: String): Observable<List<Meal>> {
        return localDataSource
            .getByName(name)
            .map {
                it.map {
                    Meal(it.id, it.name,it.categorie,it.area, it.instructions,
                        it.urlImage,it.tags, it.urlYT,it.ingredients,it.measures)
                }
            }
    }

    override fun insert(meal: Meal): Completable {
        val mealEntity = MealEntity(meal.id, meal.name, meal.categorie,meal.area, meal.instructions,
            meal.urlImage,meal.tags, meal.urlYT,meal.ingredients,meal.measures)
        return localDataSource
            .insert(mealEntity)
    }

}