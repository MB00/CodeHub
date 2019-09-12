package mb00.android.codehub.ui.search.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.FragmentSearchReposBinding
import mb00.android.codehub.ui.base.view.BaseBindingFragment
import mb00.android.codehub.ui.repo.adapter.RepoAdapter
import mb00.android.codehub.ui.search.adapter.SearchFragmentPagerAdapter
import mb00.android.codehub.ui.search.viewmodel.SearchReposViewModel
import timber.log.Timber

/**
 * Fragment containing repository search results; launched from [SearchFragmentPagerAdapter]
 */

class SearchReposFragment : BaseBindingFragment<FragmentSearchReposBinding, SearchReposViewModel>() {

    private var authHeader: String? = null
    private var repo: String? = null

    override fun layout(): Int {
        return R.layout.fragment_search_repos
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

        repo = arguments?.getString(BundleKeys.SEARCH_QUERY_KEY)
        repoCall(authHeader, repo)
    }

    private fun initPreferences() {
        val preferences = activity?.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences?.getString(PreferenceKeys.AUTH_HEADER, "")
    }

    private fun initRecyclerView() {
        binding.searchReposRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.searchReposRecyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initSwipeRefreshLayout() {
        binding.searchReposSwipeRefreshLayout.setOnRefreshListener {
            repoCall(authHeader, repo)
            binding.searchReposSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun repoCall(header: String?, repo: String?) {
        disposables.add(viewModel.searchRepos(header!!, repo!!)
                .subscribe({ repoResult ->
                    if (repoResult.items?.isNotEmpty()!!) {
                        val searchReposAdapter = repoResult.items?.let { RepoAdapter(it) }
                        binding.searchReposRecyclerView.adapter = searchReposAdapter
                    } else {
                        binding.noRepoResultsTextView.visibility = View.VISIBLE
                    }
                }, { error ->
                    binding.noRepoResultsTextView.visibility = View.VISIBLE
                    Timber.e(error.message)
                }))
    }

}
