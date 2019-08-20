package mb00.android.codehub.ui.search.view

import android.os.Bundle
import mb00.android.codehub.R
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.databinding.ActivitySearchBinding
import mb00.android.codehub.ui.base.view.BaseDrawerActivity
import mb00.android.codehub.ui.home.view.HomeActivity
import mb00.android.codehub.ui.search.adapter.SearchFragmentPagerAdapter
import mb00.android.codehub.ui.search.viewmodel.SearchViewModel

/**
 * Launched from [HomeActivity] if searchButton is clicked
 * Immediately launches [SearchFragmentPagerAdapter]
 */

class SearchActivity : BaseDrawerActivity<ActivitySearchBinding, SearchViewModel>() {

    private var queryBundle: Bundle? = null
    private var searchQuery: String? = null

    override fun layout(): Int {
        return R.layout.activity_search
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSearchQuery()
        initSearchListeners()
        initViewPager()
    }

    private fun initSearchQuery() {
        queryBundle = Bundle()
        searchQuery = ""
        queryBundle?.putString(BundleKeys.SEARCH_QUERY_KEY, searchQuery)
    }

    private fun initSearchListeners() {
        binding.searchQueryEditText.setOnEditorActionListener { _, _, _ ->
            search()
            true
        }
    }

    private fun initViewPager() {
        val searchPagerAdapter = SearchFragmentPagerAdapter(supportFragmentManager, this, queryBundle!!)
        binding.searchViewPager.adapter = searchPagerAdapter
        binding.searchViewPager.offscreenPageLimit = 3
        binding.searchViewPager.pageMargin = 4
        binding.searchViewPager.setPageMarginDrawable(R.color.grey)
        binding.searchTabLayout.setupWithViewPager(binding.searchViewPager)
    }

    fun search() {
        searchQuery = ""
        queryBundle?.putString(BundleKeys.SEARCH_QUERY_KEY, searchQuery)
        searchQuery = binding.searchQueryEditText.text.toString()
        queryBundle?.putString(BundleKeys.SEARCH_QUERY_KEY, searchQuery)
        binding.searchViewPager.adapter?.notifyDataSetChanged()
    }

}
