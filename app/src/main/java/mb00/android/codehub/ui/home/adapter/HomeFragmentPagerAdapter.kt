package mb00.android.codehub.ui.home.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.ui.home.view.HomeActivity
import mb00.android.codehub.ui.user.view.HomeFollowersFragment
import mb00.android.codehub.ui.user.view.HomeFollowingFragment
import mb00.android.codehub.ui.user.view.HomePulseFragment
import mb00.android.codehub.ui.user.view.HomeReposFragment

/**
 * FragmentPagerAdapter used to display home-related Fragments; launched from [HomeActivity]
 */

class HomeFragmentPagerAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager) {

    private val PAGE_COUNT = 4

    private val pulseTabTitle: String = context.resources.getString(R.string.pulse)
    private val reposTabTitle: String = context.resources.getString(R.string.repositories)
    private val followersTabTitle: String = context.resources.getString(R.string.followers)
    private val followingTabTitle: String = context.resources.getString(R.string.following)

    private val homeArgs: Bundle = Bundle()
    private val userLogin: String? = context
            .getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
            .getString(PreferenceKeys.USER_NAME, "")

    init {
        homeArgs.putString(BundleKeys.USER_NAME, userLogin)
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> getHomePulseFragment()
            1 -> getHomeReposFragment()
            2 -> getHomeFollowersFragment()
            3 -> getHomeFollowingFragment()
            else -> return null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> pulseTabTitle
            1 -> reposTabTitle
            2 -> followersTabTitle
            3 -> followingTabTitle
            else -> null
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    private fun getHomePulseFragment(): HomePulseFragment {
        val pulseFragment = HomePulseFragment()
        pulseFragment.arguments = homeArgs
        return pulseFragment
    }

    private fun getHomeReposFragment(): HomeReposFragment {
        val reposFragment = HomeReposFragment()
        reposFragment.arguments = homeArgs
        return reposFragment
    }

    private fun getHomeFollowersFragment(): HomeFollowersFragment {
        val followersFragment = HomeFollowersFragment()
        followersFragment.arguments = homeArgs
        return followersFragment
    }

    private fun getHomeFollowingFragment(): HomeFollowingFragment {
        val followingFragment = HomeFollowingFragment()
        followingFragment.arguments = homeArgs
        return followingFragment
    }

}
