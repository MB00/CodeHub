package mb00.android.codehub.ui.user.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentUserFollowingBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.user.adapter.UserAdapter
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter
import mb00.android.codehub.ui.user.viewmodel.UserFollowingViewModel
import timber.log.Timber

/**
 * Fragment containing users following; launched from [UserFragmentPagerAdapter]
 */

class UserFollowingFragment : BaseBindingFragment<FragmentUserFollowingBinding, UserFollowingViewModel>() {

    private lateinit var userName: String
    private lateinit var authHeader: String

    override fun layout(): Int {
        return R.layout.fragment_user_following
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)

        userName = when (arguments != null) {
            true -> arguments.getString(BundleKeys.USER_NAME)
            false -> preferences.getString(PreferenceKeys.USER_NAME, "")
        }

        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "")
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        initSwipeRefreshLayout()

        userFollowingCall(authHeader, userName)
    }

    private fun initRecyclerView() {
        binding.userFollowingRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.userFollowingRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.userFollowingSwipeRefreshLayout.setOnRefreshListener {
            userFollowingCall(authHeader, userName)
            binding.userFollowingSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun userFollowingCall(header: String, user: String) {
        disposables.add(viewModel.loadUserFollowing(header, user)
                .subscribe({ userFollowingList ->
                    when (userFollowingList.isNotEmpty()) {
                        true -> binding.userFollowingRecyclerView.adapter = UserAdapter(userFollowingList)
                        false -> binding.noneFollowingTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) })
        )
    }

}