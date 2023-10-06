package rs.raf.vezbe11.data.models

import io.reactivex.Observable
import java.util.*

data class StatisticsEntry(
    val date: Date,
    val mealsAdded: Observable<Int>?
)
