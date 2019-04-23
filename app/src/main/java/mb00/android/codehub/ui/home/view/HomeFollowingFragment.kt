package mb00.android.codehub.ui.user.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentHomeFollowingBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.home.adapter.HomeFragmentPagerAdapter
import mb00.android.codehub.ui.home.viewmodel.HomeViewModel
import mb00.android.codehub.ui.user.adapter.UserAdapter
import timber.log.Timber

/**
 * Fragment containing users following; launched from [HomeFragmentPagerAdapter]
 */

class HomeFollowingFragment : BaseBindingFragment<FragmentHomeFollowingBinding, HomeViewModel>() {

    private var userName: String? = null
    private var authHeader: String? = null

    override fun layout(): Int {
        return R.layout.fragment_home_following
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

        binding.userFollowingSwipeRefreshLayout.setOnRefreshListener {
            userFollowingCall(authHeader, userName)
            binding.userFollowingSwipeRefreshLayout.isRefreshing = false
        }
        userFollowingCall(authHeader, userName)
    }

    private fun initRecyclerView() {
        binding.userFollowingRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.userFollowingRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }


    private fun userFollowingCall(header: String?, user: String?) {
        disposables.add(viewModel.loadUserFollowing(header!!, user!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userFollowingList ->
                    if (userFollowingList.isNotEmpty()) {
                        val followingAdapter = UserAdapter(userFollowingList)
                        binding.userFollowingRecyclerView.adapter = followingAdapter
                    } else {
                        binding.noneFollowingTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) }))
    }

}