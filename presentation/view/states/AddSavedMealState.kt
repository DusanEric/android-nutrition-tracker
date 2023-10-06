package rs.raf.vezbe11.presentation.view.states

sealed class AddSavedMealState{
    object Success: AddSavedMealState()
    data class Error(val message: String): AddSavedMealState()
}
