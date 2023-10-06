package rs.raf.vezbe11.presentation.view.states

sealed class AddCategorieState {
    object Success: AddCategorieState()
    data class Error(val message: String): AddCategorieState()
}
