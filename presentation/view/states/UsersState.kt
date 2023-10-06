package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.User

sealed class UsersState{
    object Loading: UsersState()
    object DataFetched: UsersState()
    data class Success(val users: List<User>): UsersState()
    data class Error(val message: String): UsersState()
}
