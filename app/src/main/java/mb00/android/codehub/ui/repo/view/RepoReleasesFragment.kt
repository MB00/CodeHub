package mb00.android.codehub.ui.repo.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentRepoReleasesBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.repo.adapter.ReleaseAdapter
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter
import mb00.android.codehub.ui.repo.viewmodel.RepoReleasesViewModel
import timber.log.Timber

/**
 * Fragment containing repository releases; launched from [RepoFragmentPagerAdapter]
 */

class RepoReleasesFragment : BaseBindingFragment<FragmentRepoReleasesBinding, RepoReleasesViewModel>() {

    private lateinit var authHeader: String
    private lateinit var userName: String
    private lateinit var repoName: String

    override fun layout(): Int {
        return R.layout.fragment_repo_releases
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences!!.getString(PreferenceKeys.AUTH_HEADER, "")
        userName = if (arguments != null) arguments.getString(BundleKeys.USER_NAME) else ""
        repoName = if (arguments != null) arguments.getString(BundleKeys.REPO_NAME) else ""
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        initSwipeRefreshLayout()

        repoReleaseCall(authHeader, userName, repoName)
    }

    private fun initRecyclerView() {
        binding.repoReleaseRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.repoReleaseRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.repoReleasesSwipeRefreshLayout.setOnRefreshListener {
            repoReleaseCall(authHeader, userName, repoName)
            binding.repoReleasesSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun repoReleaseCall(header: String, user: String, repo: String) {
        disposables.add(viewModel.loadRepoReleases(header, user, repo)
                .subscribe({ repoReleaseList ->
                    if (repoReleaseList.isNotEmpty()) {
                        val releaseAdapter = ReleaseAdapter(repoReleaseList, activity!!)
                        binding.repoReleaseRecyclerView.adapter = releaseAdapter
                    } else {
                        binding.noReleasesTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) })
        )
    }

}
