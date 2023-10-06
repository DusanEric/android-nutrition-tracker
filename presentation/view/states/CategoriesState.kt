package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.Categorie

sealed class CategoriesState {
    object Loading: CategoriesState()
    object DataFetched: CategoriesState()
    data class Success(val categories: List<Categorie>): CategoriesState()
    data class Error(val message: String): CategoriesState()
}
