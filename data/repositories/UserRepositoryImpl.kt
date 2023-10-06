package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.UserDao
import rs.raf.vezbe11.data.models.User
import rs.raf.vezbe11.data.models.UserEntity

class UserRepositoryImpl(
    private val localDataSource: UserDao,
): UserRepository {
    override fun getAll(): Observable<List<User>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    User(it.id, it.username,it.password)
                }
            }
    }

    override fun getAllByName(name: String): Observable<List<User>> {
        return localDataSource
            .getByName(name)
            .map {
                it.map {
                    User(it.id, it.username,it.password)
                }
            }
    }

    override fun insert(user: User): Completable {
        val userEntity = UserEntity(user.id, user.username, user.password)
        return localDataSource
            .insert(userEntity)
    }
}