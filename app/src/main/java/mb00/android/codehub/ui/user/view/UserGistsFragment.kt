package mb00.android.codehub.ui.user.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentUserGistsBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.gist.adapter.GistAdapter
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter
import mb00.android.codehub.ui.user.viewmodel.UserGistsViewModel
import timber.log.Timber

/**
 * Fragment containing user gists; launched from [UserFragmentPagerAdapter]
 */

class UserGistsFragment : BaseBindingFragment<FragmentUserGistsBinding, UserGistsViewModel>() {

    private lateinit var authHeader: String
    private lateinit var userName: String

    override fun layout(): Int {
        return R.layout.fragment_user_gists
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

        userGistsCall(authHeader, userName)
    }

    private fun initRecyclerView() {
        binding.userGistsRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.userGistsRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.userGistsSwipeRefreshLayout.setOnRefreshListener {
            userGistsCall(authHeader, userName)
            binding.userGistsSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun userGistsCall(header: String, user: String) {
        disposables.add(viewModel.loadUserGists(header, user)
                .subscribe({ userGistList ->
                    if (userGistList.isNotEmpty()) {
                        val searchGistsAdapter = GistAdapter(userGistList)
                        binding.userGistsRecyclerView.adapter = searchGistsAdapter
                    } else {
                        binding.noGistsTextView.visibility = View.VISIBLE
                    }
                }, { error -> Timber.e(error.message) })
        )
    }

}
