package rs.raf.vezbe11.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.vezbe11.data.datasources.local.CategorieDataBase
import rs.raf.vezbe11.data.datasources.remote.*
import rs.raf.vezbe11.data.repositories.*
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

val categorieModule = module {

    viewModel { MainViewModel(categorieRepository = get(),areaRepository = get(),ingredientRepository= get(),mealRepository= get(),
        detailMealRepository = get(), userRepository = get(), savedMealRepository = get()) }

    single<CategorieRepository> { CategorieRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<CategorieDataBase>().getCategorieDao() }

    single<CategorieService> { create(retrofit = get()) }


    single<AreaRepository> { AreaRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<CategorieDataBase>().getAreaDao() }

    single<AreaService> { create(retrofit = get()) }


    single<IngredientRepository> { IngredientRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<CategorieDataBase>().getIngredientDao() }

    single<IngredientService> { create(retrofit = get()) }


    single<MealRepository> { MealRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<CategorieDataBase>().getMealDao() }

    single<MealService> { create(retrofit = get()) }


    single<DetailMealRepository> { DetailMealRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

//    single { get<CategorieDataBase>().getMealDao() }

    single<DetailMealService> { create(retrofit = get()) }


    single<UserRepository> { UserRepositoryImpl(localDataSource = get()) }

    single { get<CategorieDataBase>().getUserDao() }


    single<SavedMealRepository> { SavedMealRepositoryImpl(localDataSource = get()) }

    single { get<CategorieDataBase>().getSavedMealDao() }

}