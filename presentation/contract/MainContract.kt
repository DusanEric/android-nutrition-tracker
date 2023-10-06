package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.vezbe11.data.models.*
import rs.raf.vezbe11.presentation.view.states.*
import java.util.*

interface MainContract {

    interface ViewModel {

//        val moviesState: LiveData<MoviesState>
//        val addDone: LiveData<AddMovieState>
//
//        fun fetchAllMovies()
//        fun getAllMovies()
//        fun getMoviesByName(name: String)
//        fun addMovie(movie: Movie)

        val categoriesState: LiveData<CategoriesState>
        val addDone: LiveData<AddCategorieState>

        val areasState: LiveData<AreasState>
        val ingredientsState: LiveData<IngredientsState>
        val mealsState: LiveData<MealsState>

        val usersState: LiveData<UsersState>
        val addUserDone: LiveData<AddUserState>

        val savedMealsState: LiveData<SavedMealsState>
        val addSavedMealDone: LiveData<AddSavedMealState>

        fun fetchAllCategories()
        fun getAllCategories()
        fun getCategoriesByName(name: String)
        fun addCategorie(categorie: Categorie)

        fun fetchAllAreas()
        fun getAllAreas()
        fun getAreasByName(name: String)

        fun fetchAllIngredinets()
        fun getAllIngredinets()
        fun getIngredinetsByName(name: String)

        fun fetchAllMeals(category: String)
        fun getAllMeals()
        fun getMealsByName(name: String)
        fun getMealById(id: String): Observable<List<Meal>>

        fun fetchMeal(id: String)
        fun getMeal()

        fun getAllUsers()
        fun addUser(user: User)

        fun getAllSavedMeals()
        fun addSavedMeal(savedMeal: SavedMeal)
        fun getMealsAddedForDate(date: Date): Observable<Int>?

        fun getMostSavedMealsByName()
        fun getLowestSavedMealsByName()

    }

}