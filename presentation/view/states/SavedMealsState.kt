package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.SavedMeal

sealed class SavedMealsState {
    object Loading: SavedMealsState()
    object DataFetched: SavedMealsState()
    data class Success(val savedMeals: List<SavedMeal>): SavedMealsState()
    data class Error(val message: String): SavedMealsState()
}
