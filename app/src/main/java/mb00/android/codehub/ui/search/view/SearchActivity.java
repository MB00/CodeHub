package mb00.android.codehub.ui.search.view;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;

import mb00.android.codehub.R;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.databinding.ActivitySearchBinding;
import mb00.android.codehub.ui.base.view.BaseDrawerActivity;
import mb00.android.codehub.ui.home.view.HomeActivity;
import mb00.android.codehub.ui.search.adapter.SearchFragmentPagerAdapter;
import mb00.android.codehub.ui.search.viewmodel.SearchViewModel;

/**
 * Launched from {@link HomeActivity} if searchButton is clicked
 * Immediately launches {@link SearchFragmentPagerAdapter}
 */

public class SearchActivity extends BaseDrawerActivity<ActivitySearchBinding, SearchViewModel> {

    private Bundle queryBundle;
    private String searchQuery;

    @Override
    protected int layout() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initSearchQuery();
        initSearchListeners();
        initViewPager();
    }

    private void initSearchQuery() {
        queryBundle = new Bundle();
        searchQuery = "";
        queryBundle.putString(BundleKeys.SEARCH_QUERY_KEY, searchQuery);
    }

    private void initSearchListeners() {
        getBinding().searchQueryEditText.setOnEditorActionListener(((textView, i, keyEvent) -> {
            search();
            return true;
        }));
    }

    private void initViewPager() {
        PagerAdapter searchPagerAdapter = new SearchFragmentPagerAdapter(getSupportFragmentManager(), this, queryBundle);
        getBinding().searchViewPager.setAdapter(searchPagerAdapter);
        getBinding().searchViewPager.setOffscreenPageLimit(3);
        getBinding().searchViewPager.setPageMargin(4);
        getBinding().searchViewPager.setPageMarginDrawable(R.color.grey);
        getBinding().searchTabLayout.setupWithViewPager(getBinding().searchViewPager);
    }

    public void search() {
        searchQuery = "";
        queryBundle.putString(BundleKeys.SEARCH_QUERY_KEY, searchQuery);
        searchQuery = getBinding().searchQueryEditText.getText().toString();
        queryBundle.putString(BundleKeys.SEARCH_QUERY_KEY, searchQuery);
        getBinding().searchViewPager.getAdapter().notifyDataSetChanged();
    }

}
