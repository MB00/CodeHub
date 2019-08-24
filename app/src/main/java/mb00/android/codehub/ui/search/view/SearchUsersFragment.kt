package mb00.android.codehub.ui.search.view

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentSearchUsersBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.search.adapter.SearchFragmentPagerAdapter
import mb00.android.codehub.ui.search.viewmodel.SearchUsersViewModel
import mb00.android.codehub.ui.user.adapter.UserAdapter
import timber.log.Timber

/**
 * Fragment containing user search results; launched from [SearchFragmentPagerAdapter]
 */

class SearchUsersFragment : BaseBindingFragment<FragmentSearchUsersBinding, SearchUsersViewModel>() {

    private lateinit var authHeader: String
    private lateinit var user: String

    override fun layout(): Int {
        return R.layout.fragment_search_users
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPreferences()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initSwipeRefreshLayout()
    }

    override fun onResume() {
        super.onResume()

        user = arguments?.getString(BundleKeys.SEARCH_QUERY_KEY)
        userCall(authHeader, user)
    }

    private fun initPreferences() {
        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences?.getString(PreferenceKeys.AUTH_HEADER, "")
    }

    private fun initRecyclerView() {
        binding.searchUsersRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.searchUsersRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.searchUsersSwipeRefreshLayout.setOnRefreshListener {
            userCall(authHeader, user)
            binding.searchUsersSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun userCall(header: String, user: String) {
        disposables.add(viewModel.searchUsers(header, user)
                .subscribe({ userResult ->
                    if (userResult.items?.isNotEmpty()) {
                        val searchUsersAdapter = userResult.items?.let { UserAdapter(it) }
                        binding.searchUsersRecyclerView.adapter = searchUsersAdapter
                    } else {
                        binding.noUserResultsTextView.visibility = View.VISIBLE
                    }
                }, { error ->
                    binding.noUserResultsTextView.visibility = View.VISIBLE
                    Timber.e(error.message)
                })
        )
    }

}
