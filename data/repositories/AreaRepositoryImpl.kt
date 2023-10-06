package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.AreaDao
import rs.raf.vezbe11.data.datasources.remote.AreaService
import rs.raf.vezbe11.data.models.*
import timber.log.Timber

class AreaRepositoryImpl (
    private val localDataSource: AreaDao,
    private val remoteDataSource: AreaService
) : AreaRepository{


    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.allAreas.map {
                    AreaEntity(
                        it.area
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<Area>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Area(it.area)
                }
            }
    }

    override fun getAllByName(name: String): Observable<List<Area>> {
        return localDataSource
            .getByName(name)
            .map {
                it.map {
                    Area(it.area)
                }
            }
    }

    override fun insert(area: Area): Completable {
        val areaEntity = AreaEntity(area.area)
        return localDataSource
            .insert(areaEntity)
    }
}