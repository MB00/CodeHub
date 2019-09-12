package mb00.android.codehub.ui.gist.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import mb00.android.codehub.R
import mb00.android.codehub.ui.gist.view.GistActivity
import mb00.android.codehub.ui.gist.view.GistCommentsFragment
import mb00.android.codehub.ui.gist.view.GistFilesFragment

/**
 * FragmentPagerAdapter used to display gist-related Fragments; launched from [GistActivity]
 */

class GistFragmentPagerAdapter(
        private val fragmentManager: FragmentManager,
        private val context: Context,
        private val gistArgs: Bundle?
) : FragmentPagerAdapter(fragmentManager) {

    private val PAGE_COUNT = 2

    private val filesTabTitle: String = context.resources.getString(R.string.files)
    private val commentsTabTitle: String = context.resources.getString(R.string.comments)

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> getGistFilesFragment()
            1 -> getGistCommentsFragment()
            else -> getGistFilesFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> filesTabTitle
            1 -> commentsTabTitle
            else -> null
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    private fun getGistFilesFragment(): GistFilesFragment {
        val filesFragment = GistFilesFragment()
        filesFragment.arguments = gistArgs
        return filesFragment
    }

    private fun getGistCommentsFragment(): GistCommentsFragment {
        val commentsFragment = GistCommentsFragment()
        commentsFragment.arguments = gistArgs
        return commentsFragment
    }

}
