package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.Area
import rs.raf.vezbe11.data.models.Resource

interface AreaRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Area>>
    fun getAllByName(name: String): Observable<List<Area>>
    fun insert(area: Area): Completable
}