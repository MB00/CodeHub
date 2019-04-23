package mb00.android.codehub.ui.repo.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentRepoContributorsBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.repo.adapter.ContributorAdapter
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter
import mb00.android.codehub.ui.repo.viewmodel.RepoContributorsViewModel
import timber.log.Timber

/**
 * Fragment containing repository contributors; launched from [RepoFragmentPagerAdapter]
 */

class RepoContributorsFragment : BaseBindingFragment<FragmentRepoContributorsBinding, RepoContributorsViewModel>() {

    private lateinit var authHeader: String
    private lateinit var userName: String
    private lateinit var repoName: String

    private var contributorAdapter: ContributorAdapter? = null

    override fun layout(): Int {
        return R.layout.fragment_repo_contributors
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

        repoContributorCall(authHeader, userName, repoName)
    }

    private fun initRecyclerView() {
        binding.repoContributorRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.repoContributorRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.repoContributorsSwipeRefreshLayout.setOnRefreshListener {
            repoContributorCall(authHeader, userName, repoName)
            binding.repoContributorsSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun repoContributorCall(header: String, user: String, repo: String) {
        disposables.add(viewModel.loadRepoContributors(header, user, repo)
                .subscribe({ repoContributorsList ->
                    if (repoContributorsList.isNotEmpty()) {
                        contributorAdapter = ContributorAdapter(repoContributorsList)
                        binding.repoContributorRecyclerView.adapter = contributorAdapter
                    } else {
                        binding.noContributorsTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) })
        )
    }

}
