package mb00.android.codehub.ui.search.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentSearchIssuesBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.repo.adapter.IssueAdapter
import mb00.android.codehub.ui.search.adapter.SearchFragmentPagerAdapter
import mb00.android.codehub.ui.search.viewmodel.SearchIssuesViewModel
import timber.log.Timber

/**
 * Fragment containing issue search results; launched from [SearchFragmentPagerAdapter]
 */

class SearchIssuesFragment : BaseBindingFragment<FragmentSearchIssuesBinding, SearchIssuesViewModel>() {

    private var authHeader: String? = null
    private var issue: String? = null

    override fun layout(): Int {
        return R.layout.fragment_search_issues
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

        issue = arguments?.getString(BundleKeys.SEARCH_QUERY_KEY)
        issueCall(authHeader, issue)
    }

    private fun initPreferences() {
        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences?.getString(PreferenceKeys.AUTH_HEADER, "")
    }

    private fun initRecyclerView() {
        binding.searchIssuesRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.searchIssuesRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.searchIssuesSwipeRefreshLayout.setOnRefreshListener {
            issueCall(authHeader, issue)
            binding.searchIssuesSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun issueCall(header: String?, issue: String?) {
        disposables.add(viewModel.searchIssues(header!!, issue!!)
                .subscribe({ issueResult ->
                    if (issueResult.items?.isNotEmpty()!!) {
                        val searchIssuesAdapter = issueResult.items?.let { IssueAdapter(it) }
                        binding.searchIssuesRecyclerView.adapter = searchIssuesAdapter
                    } else {
                        binding.noIssueResultsTextView.visibility = View.VISIBLE
                    }
                }, { error ->
                    binding.noIssueResultsTextView.visibility = View.VISIBLE
                    Timber.e(error.message)
                })
        )
    }

}
