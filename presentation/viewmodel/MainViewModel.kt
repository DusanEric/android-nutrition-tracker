package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.vezbe11.data.models.*
import rs.raf.vezbe11.data.repositories.*
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.states.*
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val categorieRepository: CategorieRepository,
    private val areaRepository: AreaRepository,
    private val ingredientRepository: IngredientRepository,
    private val mealRepository: MealRepository,
    private val detailMealRepository: DetailMealRepository,
    private val userRepository: UserRepository,
    private val savedMealRepository: SavedMealRepository,
    ) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val categoriesState: MutableLiveData<CategoriesState> = MutableLiveData()
    override val addDone: MutableLiveData<AddCategorieState> = MutableLiveData()

    override val areasState: MutableLiveData<AreasState> = MutableLiveData()

    override val ingredientsState: MutableLiveData<IngredientsState> = MutableLiveData()

    override val mealsState: MutableLiveData<MealsState> = MutableLiveData()

    override val usersState: MutableLiveData<UsersState> = MutableLiveData()
    override val addUserDone: MutableLiveData<AddUserState> = MutableLiveData()

    override val savedMealsState: MutableLiveData<SavedMealsState> = MutableLiveData()
    override val addSavedMealDone: MutableLiveData<AddSavedMealState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
    }

    override fun fetchAllCategories() {
        val subscription = categorieRepository
            .fetchAll()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> categoriesState.value = CategoriesState.Loading
                        is Resource.Success -> categoriesState.value = CategoriesState.DataFetched
                        is Resource.Error -> categoriesState.value = CategoriesState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    categoriesState.value = CategoriesState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllCategories() {
        val subscription = categorieRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    categoriesState.value = CategoriesState.Success(it)
                },
                {
                    categoriesState.value = CategoriesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getCategoriesByName(name: String) {
        publishSubject.onNext(name)
    }

    override fun addCategorie(categorie: Categorie) {
        val subscription = categorieRepository
            .insert(categorie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddCategorieState.Success
                },
                {
                    addDone.value = AddCategorieState.Error("Error happened while adding movie")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllAreas() {
        val subscription = areaRepository
            .fetchAll()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> areasState.value = AreasState.Loading
                        is Resource.Success -> areasState.value = AreasState.DataFetched
                        is Resource.Error -> areasState.value = AreasState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    areasState.value = AreasState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllAreas() {
        val subscription = areaRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    areasState.value = AreasState.Success(it)
                },
                {
                    areasState.value = AreasState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAreasByName(name: String) {
        publishSubject.onNext(name)
    }

    override fun fetchAllIngredinets() {
        val subscription = ingredientRepository
            .fetchAll()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> ingredientsState.value = IngredientsState.Loading
                        is Resource.Success -> ingredientsState.value = IngredientsState.DataFetched
                        is Resource.Error -> ingredientsState.value = IngredientsState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    ingredientsState.value = IngredientsState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllIngredinets() {
        val subscription = ingredientRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    ingredientsState.value = IngredientsState.Success(it)
                },
                {
                    ingredientsState.value = IngredientsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getIngredinetsByName(name: String) {
        publishSubject.onNext(name)
    }

    override fun fetchAllMeals(category: String) {
        val subscription = mealRepository
            .fetchAll(category)
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> mealsState.value = MealsState.Loading
                        is Resource.Success -> mealsState.value = MealsState.DataFetched
                        is Resource.Error -> mealsState.value = MealsState.Error("Error happened while fetching data from the server=>" + category)
                    }
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from the server=>" + category)
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllMeals() {
        val subscription = mealRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealsState.value = MealsState.Success(it)
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getMealsByName(name: String) {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                mealRepository
                    .getAllByName(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    mealsState.value = MealsState.Success(it)
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
        publishSubject.onNext(name)
    }

    override fun getMealById(id: String): Observable<List<Meal>> {
        return publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { mealRepository.getById(it) } // Assuming mealRepository.getById(id) returns Observable<List<Meal>>
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Timber.e("Error in publish subject")
                Timber.e(it)
            }
            .map { listOfMeals ->
                // Process the list of meals if needed
                listOfMeals
            }
            .doOnNext { meals ->
                mealsState.value = MealsState.Success(meals)
            }
            .doOnError { error ->
                mealsState.value = MealsState.Error("Error happened while fetching data from db")
                Timber.e(error)
            }
            .doOnSubscribe {
                subscriptions.add(it)
                publishSubject.onNext(id)
            }
    }




    override fun fetchMeal(id: String) {
        val subscription = detailMealRepository
            .fetchAll(id)
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> mealsState.value = MealsState.Loading
                        is Resource.Success -> mealsState.value = MealsState.DataFetched
                        is Resource.Error -> mealsState.value = MealsState.Error("Error happened while fetching data from the server=>" + id)
                    }
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from the server=>" + id)
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getMeal() {
        val subscription = detailMealRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealsState.value = MealsState.Success(it)
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllUsers() {
        val subscription = userRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    usersState.value = UsersState.Success(it)
                },
                {
                    usersState.value = UsersState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun addUser(user: User) {
        val subscription = userRepository
            .insert(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addUserDone.value = AddUserState.Success
                },
                {
                    addUserDone.value = AddUserState.Error("Error happened while adding user")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllSavedMeals() {
        val subscription = savedMealRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    savedMealsState.value = SavedMealsState.Success(it)
                },
                {
                    savedMealsState.value = SavedMealsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun addSavedMeal(savedMeal: SavedMeal) {
        val subscription = savedMealRepository
            .insert(savedMeal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addSavedMealDone.value = AddSavedMealState.Success
                },
                {
                    addSavedMealDone.value = AddSavedMealState.Error("Error happened while adding user")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getMealsAddedForDate(date: Date): Observable<Int>? {

        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        val startOfDay = calendar.time

        calendar.set(Calendar.HOUR_OF_DAY, 23)
        val endOfDay = calendar.time

        return savedMealRepository
            .getMealsAddedBetweenDates(startOfDay, endOfDay)
            .map { it.size }
    }

    override fun getMostSavedMealsByName(){
        val subscription = savedMealRepository
            .getMostSavedMealsByName()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mostSavedMeals ->
                    savedMealsState.value = SavedMealsState.Success(mostSavedMeals)
                },
                { error ->
                    savedMealsState.value = SavedMealsState.Error("Error happened while fetching most saved meals by name")
                    Timber.e(error)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getLowestSavedMealsByName() {
        val subscription = savedMealRepository
            .getLowestSavedMealsByName()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mostSavedMeals ->
                    savedMealsState.value = SavedMealsState.Success(mostSavedMeals)
                },
                { error ->
                    savedMealsState.value = SavedMealsState.Error("Error happened while fetching most saved meals by name")
                    Timber.e(error)
                }
            )
        subscriptions.add(subscription)
    }


    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}