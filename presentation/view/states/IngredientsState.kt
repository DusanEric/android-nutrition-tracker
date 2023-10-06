package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.Ingredient

sealed class IngredientsState {
    object Loading: IngredientsState()
    object DataFetched: IngredientsState()
    data class Success(val ingredients: List<Ingredient>): IngredientsState()
    data class Error(val message: String): IngredientsState()
}
