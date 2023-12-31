package rs.raf.vezbe11.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.vezbe11.R
import rs.raf.vezbe11.presentation.view.fragments.*

class MainPagerAdapter(
    fragmentManager: FragmentManager,
    private val context: Context
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 6
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
        const val FRAGMENT_3 = 2
        const val FRAGMENT_4 = 3
        const val FRAGMENT_5 = 4
        const val FRAGMENT_6 = 5
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_1 -> ListFragment()
            FRAGMENT_2 -> ListFilterFragment()
            FRAGMENT_3 -> SavedFragment()
            FRAGMENT_4 -> StatisticsFragment()
            FRAGMENT_5 -> PlanFragment()
            else -> UserStatFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_1 -> context.getString(R.string.categories)
            FRAGMENT_2 -> context.getString(R.string.filter)
            FRAGMENT_3 -> context.getString(R.string.save)
            FRAGMENT_4 -> context.getString(R.string.statistics)
            FRAGMENT_5 -> context.getString(R.string.plan)
            else -> context.getString(R.string.uStat)
        }
    }

}