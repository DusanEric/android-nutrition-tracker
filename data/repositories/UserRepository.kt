package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.User

interface UserRepository {

    fun getAll(): Observable<List<User>>
    fun getAllByName(name: String): Observable<List<User>>
    fun insert(user: User): Completable
}