package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.Categorie
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.data.models.Resource

interface CategorieRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Categorie>>
    fun getAllByName(name: String): Observable<List<Categorie>>
    fun insert(categorie: Categorie): Completable
}