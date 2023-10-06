package rs.raf.vezbe11.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.vezbe11.data.models.*

@Database(
    entities = [CategorieEntity::class, AreaEntity::class, IngredientEntity::class, MealEntity::class,
        UserEntity::class, SavedMealEntity::class],
    version = 15,
    exportSchema = false)
@TypeConverters(ListTypeConverter::class, DateTypeConverter::class)
abstract class CategorieDataBase : RoomDatabase() {
    abstract fun getCategorieDao(): CategorieDao
    abstract fun getAreaDao(): AreaDao
    abstract fun getIngredientDao(): IngredientDao
    abstract fun getMealDao(): MealDao
    abstract fun getUserDao(): UserDao
    abstract fun getSavedMealDao(): SavedMealDao
}