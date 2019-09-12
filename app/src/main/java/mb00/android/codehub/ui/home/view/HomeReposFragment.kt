package mb00.android.codehub.ui.home.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentHomeReposBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.home.adapter.HomeFragmentPagerAdapter
import mb00.android.codehub.ui.home.viewmodel.HomeViewModel
import mb00.android.codehub.ui.repo.adapter.RepoAdapter
import timber.log.Timber

/**
 * Fragment containing user repositories; launched from [HomeFragmentPagerAdapter]
 */

class HomeReposFragment : BaseBindingFragment<FragmentHomeReposBinding, HomeViewModel>() {

    private var userLogin: String? = null
    private var authHeader: String? = null

    override fun layout(): Int {
        return R.layout.fragment_home_repos
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)

        userLogin = if (arguments != null) {
            arguments?.getString(BundleKeys.USER_NAME)
        } else {
            preferences?.getString(PreferenceKeys.USER_NAME, "")
        }
        authHeader = preferences?.getString(PreferenceKeys.AUTH_HEADER, "")
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()

        binding.userReposSwipeRefreshLayout.setOnRefreshListener {
            userReposCall(authHeader, userLogin)
            binding.userReposSwipeRefreshLayout.isRefreshing = false
        }
        userReposCall(authHeader, userLogin)
    }

    private fun initRecyclerView() {
        binding.userReposRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.userReposRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun userReposCall(header: String?, user: String?) {
        disposables.add(viewModel.loadUserRepos(header!!, user!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userRepoList ->
                    if (userRepoList.isNotEmpty()) {
                        val searchReposAdapter = RepoAdapter(userRepoList)
                        binding.userReposRecyclerView.adapter = searchReposAdapter
                    } else {
                        binding.noReposTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) }))
    }

}
