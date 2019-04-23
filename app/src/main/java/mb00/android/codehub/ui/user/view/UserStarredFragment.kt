package mb00.android.codehub.ui.user.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentUserStarredBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.repo.adapter.RepoAdapter
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter
import mb00.android.codehub.ui.user.viewmodel.UserStarredViewModel
import timber.log.Timber

/**
 * Fragment containing user-starred repositories; launched from [UserFragmentPagerAdapter]
 */

class UserStarredFragment : BaseBindingFragment<FragmentUserStarredBinding, UserStarredViewModel>() {

    private lateinit var authHeader: String
    private lateinit var userName: String

    override fun layout(): Int {
        return R.layout.fragment_user_starred
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences!!.getString(PreferenceKeys.AUTH_HEADER, "")
        userName = if (arguments != null) arguments.getString(BundleKeys.USER_NAME) else ""
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        initSwipeRefreshLayout()

        userStarredCall(authHeader, userName)
    }

    private fun initRecyclerView() {
        binding.userStarredRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.userStarredRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.userStarredSwipeRefreshLayout.setOnRefreshListener {
            userStarredCall(authHeader, userName)
            binding.userStarredSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun userStarredCall(header: String?, user: String?) {
        disposables.add(viewModel.loadUserStarred(header!!, user!!)
                .subscribe({ userStarredList ->
                    if (userStarredList.isNotEmpty()) {
                        val starredAdapter = RepoAdapter(userStarredList)
                        binding.userStarredRecyclerView.adapter = starredAdapter
                    } else {
                        binding.noneStarredTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) })
        )
    }

}
