package mb00.android.codehub.ui.repo.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentRepoPullRequestsBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.repo.adapter.PullRequestAdapter
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter
import mb00.android.codehub.ui.repo.viewmodel.RepoPullRequestsViewModel
import timber.log.Timber

/**
 * Fragment containing repository pull requests; launched from [RepoFragmentPagerAdapter]
 */

class RepoPullRequestsFragment : BaseBindingFragment<FragmentRepoPullRequestsBinding, RepoPullRequestsViewModel>() {

    private lateinit var authHeader: String
    private lateinit var userName: String
    private lateinit var repoName: String

    override fun layout(): Int {
        return R.layout.fragment_repo_pull_requests
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences?.getString(PreferenceKeys.AUTH_HEADER, "") ?: ""
        userName = if (arguments != null) arguments.getString(BundleKeys.USER_NAME) else ""
        repoName = if (arguments != null) arguments.getString(BundleKeys.REPO_NAME) else ""
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        initSwipeRefreshLayout()

        repoPullRequestCall(authHeader, userName, repoName)
    }

    private fun initRecyclerView() {
        binding.repoPullRequestsRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.repoPullRequestsRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.repoPullRequestsSwipeRefreshLayout.setOnRefreshListener {
            repoPullRequestCall(authHeader, userName, repoName)
            binding.repoPullRequestsSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun repoPullRequestCall(header: String, user: String, repo: String) {
        disposables.add(viewModel.loadRepoPullRequests(header, user, repo)
                .subscribe({ repoPullRequestList ->
                    if (repoPullRequestList.isNotEmpty()) {
                        val pullRequestAdapter = PullRequestAdapter(repoPullRequestList, activity!!)
                        binding.repoPullRequestsRecyclerView.adapter = pullRequestAdapter
                    } else {
                        binding.noPullRequestsTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) })
        )
    }

}
