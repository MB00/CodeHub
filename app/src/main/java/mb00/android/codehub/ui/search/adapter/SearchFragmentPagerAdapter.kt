package mb00.android.codehub.ui.search.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import mb00.android.codehub.R
import mb00.android.codehub.ui.search.view.SearchActivity
import mb00.android.codehub.ui.search.view.SearchIssuesFragment
import mb00.android.codehub.ui.search.view.SearchReposFragment
import mb00.android.codehub.ui.search.view.SearchUsersFragment

/**
 * FragmentPagerAdapter used to display search-related Fragments; launched from [SearchActivity]
 */

class SearchFragmentPagerAdapter(
        private val fragmentManager: FragmentManager,
        private val context: Context,
        private val queryArgs: Bundle
) : FragmentPagerAdapter(fragmentManager) {

    private val PAGE_COUNT = 3

    private val reposTabTitle: String = context.resources.getString(R.string.repositories)
    private val usersTabTitle: String = context.resources.getString(R.string.users)
    private val issuesTabTitle: String = context.resources.getString(R.string.issues)

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> getSearchReposFragment()
            1 -> getSearchUsersFragment()
            2 -> getSearchIssuesFragment()
            else -> null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> reposTabTitle
            1 -> usersTabTitle
            2 -> issuesTabTitle
            else -> null
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    private fun getSearchReposFragment(): SearchReposFragment {
        val searchReposFragment = SearchReposFragment()
        searchReposFragment.arguments = queryArgs
        return searchReposFragment
    }

    private fun getSearchUsersFragment(): SearchUsersFragment {
        val searchUsersFragment = SearchUsersFragment()
        searchUsersFragment.arguments = queryArgs
        return searchUsersFragment
    }

    private fun getSearchIssuesFragment(): SearchIssuesFragment {
        val searchIssuesFragment = SearchIssuesFragment()
        searchIssuesFragment.arguments = queryArgs
        return searchIssuesFragment
    }

}
