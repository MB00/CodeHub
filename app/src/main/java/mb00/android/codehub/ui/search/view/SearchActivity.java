package mb00.android.codehub.ui.search.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import mb00.android.codehub.R;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.base.view.NavigationDrawerActivity;
import mb00.android.codehub.ui.home.view.HomeActivity;
import mb00.android.codehub.ui.search.adapter.SearchFragmentPagerAdapter;

/**
 * Launched from {@link HomeActivity} if searchButton is clicked
 * Immediately launches {@link SearchFragmentPagerAdapter}
 */

public class SearchActivity extends NavigationDrawerActivity {

    //==============================================================================================
    // SearchActivity fields
    //==============================================================================================

    private EditText searchQueryEditText;
    private ImageButton backButton;
    private ImageButton searchButton;
    public static String searchQuery;
    private Bundle queryBundle;
    private ViewPager searchViewPager;
    private PagerAdapter searchPagerAdapter;
    private TabLayout searchTabLayout;

    //==============================================================================================
    // Activity / lifecycle methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        super.setupNavigationDrawer(this);

        queryBundle = new Bundle();
        queryBundle.putString(BundleKeys.SEARCH_QUERY_KEY, searchQuery);

        backButton = findViewById(R.id.search_back_button);
        searchQueryEditText = findViewById(R.id.search_query_edit_text);
        searchButton = findViewById(R.id.main_search_button);
        searchPagerAdapter = new SearchFragmentPagerAdapter(getSupportFragmentManager(), this, queryBundle);
        searchViewPager = findViewById(R.id.search_view_pager);
        searchTabLayout = findViewById(R.id.search_tab_layout);

        searchViewPager.setAdapter(searchPagerAdapter);
        searchViewPager.setOffscreenPageLimit(3);
        searchTabLayout.setupWithViewPager(searchViewPager);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        searchQuery = "";
        queryBundle.putString(BundleKeys.SEARCH_QUERY_KEY, searchQuery);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchQuery = searchQueryEditText.getText().toString();
                queryBundle.putString(BundleKeys.SEARCH_QUERY_KEY, searchQuery);
                searchViewPager.getAdapter().notifyDataSetChanged();
            }
        });
    }

}
