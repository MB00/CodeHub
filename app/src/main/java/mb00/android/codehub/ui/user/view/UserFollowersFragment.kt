package mb00.android.codehub.ui.user.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentUserFollowersBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.user.adapter.UserAdapter
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter
import mb00.android.codehub.ui.user.viewmodel.UserFollowersViewModel
import timber.log.Timber

/**
 * Fragment containing user followers; launched from [UserFragmentPagerAdapter]
 */

class UserFollowersFragment : BaseBindingFragment<FragmentUserFollowersBinding, UserFollowersViewModel>() {

    private lateinit var userName: String
    private lateinit var authHeader: String

    override fun layout(): Int {
        return R.layout.fragment_user_followers
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)

        userName = arguments?.getString(BundleKeys.USER_NAME) ?: ""
        authHeader = preferences?.getString(PreferenceKeys.AUTH_HEADER, "") ?: ""
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()
        initSwipeRefreshLayout()

        userFollowersCall(authHeader, userName)
    }

    private fun initRecyclerView() {
        binding.userFollowersRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.userFollowersRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.userFollowersSwipeRefreshLayout.setOnRefreshListener {
            userFollowersCall(authHeader, userName)
            binding.userFollowersSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun userFollowersCall(header: String, user: String) {
        disposables.add(viewModel.loadUserFollowers(header, user)
                .subscribe({ userFollowersList ->
                    when (userFollowersList.isNotEmpty()) {
                        true -> binding.userFollowersRecyclerView.adapter = UserAdapter(userFollowersList)
                        false -> binding.noFollowersTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) })
        )
    }

}
