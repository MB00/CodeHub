package mb00.android.codehub.ui.repo.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import mb00.android.codehub.R
import mb00.android.codehub.ui.repo.view.RepoActivity
import mb00.android.codehub.ui.repo.view.RepoCodeFragment
import mb00.android.codehub.ui.repo.view.RepoCommitsFragment
import mb00.android.codehub.ui.repo.view.RepoContributorsFragment
import mb00.android.codehub.ui.repo.view.RepoIssuesFragment
import mb00.android.codehub.ui.repo.view.RepoLicenseFragment
import mb00.android.codehub.ui.repo.view.RepoPullRequestsFragment
import mb00.android.codehub.ui.repo.view.RepoPulseFragment
import mb00.android.codehub.ui.repo.view.RepoReadmeFragment
import mb00.android.codehub.ui.repo.view.RepoReleasesFragment

/**
 * FragmentPagerAdapter used to display repository-related Fragments; launched from [RepoActivity]
 */

class RepoFragmentPagerAdapter(
        private val fragmentManager: FragmentManager,
        private val context: Context,
        private val repoArgs: Bundle?
) : FragmentPagerAdapter(fragmentManager) {

    private val PAGE_COUNT = 9

    private val readmeTabTitle: String = context.resources.getString(R.string.readme)
    private val codeTabTitle: String = context.resources.getString(R.string.code)
    private val issuesTabTitle: String = context.resources.getString(R.string.issues)
    private val pullRequestsTabTitle: String = context.resources.getString(R.string.pull_requests)
    private val pulseTabTitle: String = context.resources.getString(R.string.pulse)
    private val commitsTabTitle: String = context.resources.getString(R.string.commits)
    private val releasesTabTitle: String = context.resources.getString(R.string.releases)
    private val contributorsTabTitle: String = context.resources.getString(R.string.contributors)
    private val licenseTabTitle: String = context.resources.getString(R.string.license)

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> getRepoReadmeFragment()
            1 -> getRepoCodeFragment()
            2 -> getRepoIssuesFragment()
            3 -> getRepoPullRequestsFragment()
            4 -> getRepoPulseFragment()
            5 -> getRepoCommitsFragment()
            6 -> getRepoReleasesFragment()
            7 -> getRepoContributorsFragment()
            8 -> getRepoLicenseFragment()
            else -> getRepoReadmeFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> readmeTabTitle
            1 -> codeTabTitle
            2 -> issuesTabTitle
            3 -> pullRequestsTabTitle
            4 -> pulseTabTitle
            5 -> commitsTabTitle
            6 -> releasesTabTitle
            7 -> contributorsTabTitle
            8 -> licenseTabTitle
            else -> null
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    private fun getRepoReadmeFragment(): RepoReadmeFragment {
        val readmeFragment = RepoReadmeFragment()
        readmeFragment.arguments = repoArgs
        return readmeFragment
    }

    private fun getRepoCodeFragment(): RepoCodeFragment {
        val codeFragment = RepoCodeFragment()
        codeFragment.arguments = repoArgs
        return codeFragment
    }

    private fun getRepoIssuesFragment(): RepoIssuesFragment {
        val issuesFragment = RepoIssuesFragment()
        issuesFragment.arguments = repoArgs
        return issuesFragment
    }

    private fun getRepoPullRequestsFragment(): RepoPullRequestsFragment {
        val pullRequestsFragment = RepoPullRequestsFragment()
        pullRequestsFragment.arguments = repoArgs
        return pullRequestsFragment
    }

    private fun getRepoPulseFragment(): RepoPulseFragment {
        val pulseFragment = RepoPulseFragment()
        pulseFragment.arguments = repoArgs
        return pulseFragment
    }

    private fun getRepoCommitsFragment(): RepoCommitsFragment {
        val commitsFragment = RepoCommitsFragment()
        commitsFragment.arguments = repoArgs
        return commitsFragment
    }

    private fun getRepoReleasesFragment(): RepoReleasesFragment {
        val releasesFragment = RepoReleasesFragment()
        releasesFragment.arguments = repoArgs
        return releasesFragment
    }

    private fun getRepoContributorsFragment(): RepoContributorsFragment {
        val contributorsFragment = RepoContributorsFragment()
        contributorsFragment.arguments = repoArgs
        return contributorsFragment
    }

    private fun getRepoLicenseFragment(): RepoLicenseFragment {
        val licenseFragment = RepoLicenseFragment()
        licenseFragment.arguments = repoArgs
        return licenseFragment
    }

}
