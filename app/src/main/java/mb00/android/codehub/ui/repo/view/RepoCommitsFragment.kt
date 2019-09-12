package mb00.android.codehub.ui.repo.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentRepoCommitsBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.repo.adapter.CommitAdapter
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter
import mb00.android.codehub.ui.repo.viewmodel.RepoCommitsViewModel
import timber.log.Timber

/**
 * Fragment containing repository commits; launched from [RepoFragmentPagerAdapter]
 */

class RepoCommitsFragment : BaseBindingFragment<FragmentRepoCommitsBinding, RepoCommitsViewModel>() {

    private lateinit var authHeader: String
    private lateinit var userName: String
    private lateinit var repoName: String

    private var commitAdapter: CommitAdapter? = null

    override fun layout(): Int {
        return R.layout.fragment_repo_commits
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

        repoCommitCall(authHeader, userName, repoName)
    }

    private fun initRecyclerView() {
        binding.repoCommitRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.repoCommitRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.repoCommitsSwipeRefreshLayout.setOnRefreshListener {
            repoCommitCall(authHeader, userName, repoName)
            binding.repoCommitsSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun repoCommitCall(header: String, user: String, repo: String) {
        disposables.add(viewModel.loadRepoCommits(header, user, repo)
                .subscribe({ repoCommitsList ->
                    if (repoCommitsList.isNotEmpty()) {
                        commitAdapter = CommitAdapter(repoCommitsList, activity!!)
                        binding.repoCommitRecyclerView.adapter = commitAdapter
                    } else {
                        binding.noCommitsTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) })
        )
    }

}
