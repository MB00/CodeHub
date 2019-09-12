package mb00.android.codehub.ui.user.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import mb00.android.codehub.R
import mb00.android.codehub.ui.user.view.*

/**
 * FragmentPagerAdapter used to display user-related Fragments; launched from [UserActivity]
 */

class UserFragmentPagerAdapter(
        private val fragmentManager: FragmentManager,
        private val context: Context,
        private val userArgs: Bundle?
) : FragmentPagerAdapter(fragmentManager) {

    private val PAGE_COUNT = 7

    private val overviewTabTitle: String = context.resources.getString(R.string.overview)
    private val reposTabTitle: String = context.resources.getString(R.string.repositories)
    private val starredTabTitle: String = context.resources.getString(R.string.starred)
    private val gistsTabTitle: String = context.resources.getString(R.string.gists)
    private val pulseTabTitle: String = context.resources.getString(R.string.pulse)
    private val followersTabTitle: String = context.resources.getString(R.string.followers)
    private val followingTabTitle: String = context.resources.getString(R.string.following)

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> getUserOverviewFragment()
            1 -> getUserReposFragment()
            2 -> getUserStarredFragment()
            3 -> getUserGistsStarred()
            4 -> getUserPulseFragment()
            5 -> getUserFollowersFragment()
            6 -> getUserFollowingFragment()
            else -> getUserOverviewFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> overviewTabTitle
            1 -> reposTabTitle
            2 -> starredTabTitle
            3 -> gistsTabTitle
            4 -> pulseTabTitle
            5 -> followersTabTitle
            6 -> followingTabTitle
            else -> null
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    private fun getUserOverviewFragment(): UserOverviewFragment {
        val overviewFragment = UserOverviewFragment()
        overviewFragment.arguments = userArgs
        return overviewFragment
    }

    private fun getUserReposFragment(): UserReposFragment {
        val reposFragment = UserReposFragment()
        reposFragment.arguments = userArgs
        return reposFragment
    }

    private fun getUserStarredFragment(): UserStarredFragment {
        val starredFragment = UserStarredFragment()
        starredFragment.arguments = userArgs
        return starredFragment
    }

    private fun getUserGistsStarred(): UserGistsFragment {
        val gistsFragment = UserGistsFragment()
        gistsFragment.arguments = userArgs
        return gistsFragment
    }

    private fun getUserPulseFragment(): UserPulseFragment {
        val pulseFragment = UserPulseFragment()
        pulseFragment.arguments = userArgs
        return pulseFragment
    }

    private fun getUserFollowersFragment(): UserFollowersFragment {
        val followersFragment = UserFollowersFragment()
        followersFragment.arguments = userArgs
        return followersFragment
    }

    private fun getUserFollowingFragment(): UserFollowingFragment {
        val followingFragment = UserFollowingFragment()
        followingFragment.arguments = userArgs
        return followingFragment
    }

}
