package mb00.android.codehub.ui.repo.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentRepoIssuesBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.repo.adapter.IssueAdapter
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter
import mb00.android.codehub.ui.repo.viewmodel.RepoIssuesViewModel
import timber.log.Timber

/**
 * Fragment containing repository issues; launched from [RepoFragmentPagerAdapter]
 */

class RepoIssuesFragment : BaseBindingFragment<FragmentRepoIssuesBinding, RepoIssuesViewModel>() {

    private lateinit var authHeader: String
    private lateinit var userName: String
    private lateinit var repoName: String

    override fun layout(): Int {
        return R.layout.fragment_repo_issues
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences?.getString(PreferenceKeys.AUTH_HEADER, "") ?: ""
        userName = arguments?.getString(BundleKeys.USER_NAME) ?: ""
        repoName = arguments?.getString(BundleKeys.REPO_NAME) ?: ""

    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        initSwipeRefreshLayout()

        repoIssuesCall(authHeader, userName, repoName)
    }

    private fun initRecyclerView() {
        binding.repoIssuesRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.repoIssuesRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.repoIssuesSwipeRefreshLayout.setOnRefreshListener {
            repoIssuesCall(authHeader, userName, repoName)
            binding.repoIssuesSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun repoIssuesCall(header: String, user: String, repo: String) {
        disposables.add(viewModel.loadRepoIssues(header, user, repo)
                .subscribe({ repoIssueList ->
                    if (repoIssueList.isNotEmpty()) {
                        val issueAdapter = IssueAdapter(repoIssueList)
                        binding.repoIssuesRecyclerView.adapter = issueAdapter
                    } else {
                        binding.noIssuesTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) })
        )
    }

}
