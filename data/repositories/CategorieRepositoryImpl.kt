package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.CategorieDao
import rs.raf.vezbe11.data.datasources.remote.CategorieService
import rs.raf.vezbe11.data.models.*
import timber.log.Timber

class CategorieRepositoryImpl (
    private val localDataSource: CategorieDao,
    private val remoteDataSource: CategorieService
        ) : CategorieRepository{


    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.allCategories.map {
                    CategorieEntity(
                        it.id,
                        it.name,
                        it.url,
                        it.description
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<Categorie>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Categorie(it.id, it.name,it.url,it.description)
                }
            }
    }

    override fun getAllByName(name: String): Observable<List<Categorie>> {
        return localDataSource
            .getByName(name)
            .map {
                it.map {
                    Categorie(it.id, it.name,it.url,it.description)
                }
            }
    }

    override fun insert(categorie: Categorie): Completable {
        val categorieEntity = CategorieEntity(categorie.id, categorie.name, categorie.url,categorie.description)
        return localDataSource
            .insert(categorieEntity)
    }


}