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
import mb00.android.codehub.databinding.FragmentHomeFollowersBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.home.adapter.HomeFragmentPagerAdapter
import mb00.android.codehub.ui.home.viewmodel.HomeViewModel
import mb00.android.codehub.ui.user.adapter.UserAdapter
import timber.log.Timber

/**
 * Fragment containing user followers; launched from [HomeFragmentPagerAdapter]
 */

class HomeFollowersFragment : BaseBindingFragment<FragmentHomeFollowersBinding, HomeViewModel>() {

    private var userName: String? = null
    private var authHeader: String? = null

    override fun layout(): Int {
        return R.layout.fragment_home_followers
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)

        userName = if (arguments != null) {
            arguments?.getString(BundleKeys.USER_NAME)
        } else {
            preferences?.getString(PreferenceKeys.USER_NAME, "")
        }
        authHeader = preferences?.getString(PreferenceKeys.AUTH_HEADER, "")
    }

    override fun onStart() {
        super.onStart()

        initRecyclerView()

        binding.userFollowersSwipeRefreshLayout.setOnRefreshListener {
            userFollowersCall(authHeader, userName)
            binding.userFollowersSwipeRefreshLayout.isRefreshing = false
        }
        userFollowersCall(authHeader, userName)
    }

    private fun initRecyclerView() {
        binding.userFollowersRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.userFollowersRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun userFollowersCall(header: String?, user: String?) {
        disposables.add(viewModel.loadUserFollowers(header!!, user!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userFollowersList ->
                    if (userFollowersList.isNotEmpty()) {
                        val followersAdapter = UserAdapter(userFollowersList)
                        binding.userFollowersRecyclerView.adapter = followersAdapter
                    } else {
                        binding.noFollowersTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) }))
    }

}
