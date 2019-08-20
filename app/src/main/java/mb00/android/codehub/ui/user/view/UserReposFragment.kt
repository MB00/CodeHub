package mb00.android.codehub.ui.user.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentUserReposBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.repo.adapter.RepoAdapter
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter
import mb00.android.codehub.ui.user.viewmodel.UserReposViewModel
import timber.log.Timber

/**
 * Fragment containing user repositories; launched from [UserFragmentPagerAdapter]
 */

class UserReposFragment : BaseBindingFragment<FragmentUserReposBinding, UserReposViewModel>() {

    private lateinit var userLogin: String
    private lateinit var authHeader: String

    override fun layout(): Int {
        return R.layout.fragment_user_repos
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)

        userLogin = when (arguments) {
            null -> preferences?.getString(PreferenceKeys.USER_NAME, "") ?: ""
            else -> arguments?.getString(BundleKeys.USER_NAME) ?: ""
        }
        authHeader = preferences?.getString(PreferenceKeys.AUTH_HEADER, "") ?: ""
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        initSwipeRefreshLayout()

        userReposCall(authHeader, userLogin)
    }

    private fun initRecyclerView() {
        binding.userReposRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.userReposRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.userReposSwipeRefreshLayout.setOnRefreshListener {
            userReposCall(authHeader, userLogin)
            binding.userReposSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun userReposCall(header: String, user: String) {
        disposables.add(viewModel.loadUserRepos(header, user).subscribe({ userRepoList ->
            when (userRepoList.isNotEmpty()) {
                true -> binding.userReposRecyclerView.adapter = RepoAdapter(userRepoList)
                false -> binding.noReposTextView.visibility = View.VISIBLE
            }
        }, { error -> Timber.e(error.message) })
        )
    }

}
