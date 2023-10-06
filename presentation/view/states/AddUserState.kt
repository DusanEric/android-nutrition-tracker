package rs.raf.vezbe11.presentation.view.states

sealed class AddUserState{
    object Success: AddUserState()
    data class Error(val message: String): AddUserState()
}
