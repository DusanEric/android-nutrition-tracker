package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.Meal

sealed class MealsState{
    object Loading: MealsState()
    object DataFetched: MealsState()
    data class Success(val meals: List<Meal>): MealsState()
    data class Error(val message: String): MealsState()
}
