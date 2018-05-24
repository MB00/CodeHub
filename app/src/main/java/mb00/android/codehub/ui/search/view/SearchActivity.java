package mb00.android.codehub.ui.search.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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

    public static String searchQuery;
    private Bundle queryBundle;

    private EditText searchQueryEditText;
    private ImageButton backButton;
    private ImageButton searchButton;
    private ViewPager searchViewPager;

    //==============================================================================================
    // Activity / lifecycle methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        super.setupNavigationDrawer(this);

        initialize();
        setListeners();
    }

    private void initialize() {
        searchQueryEditText = findViewById(R.id.search_query_edit_text);
        backButton = findViewById(R.id.search_back_button);
        searchButton = findViewById(R.id.main_search_button);
        searchViewPager = findViewById(R.id.search_view_pager);
        TabLayout searchTabLayout = findViewById(R.id.search_tab_layout);

        queryBundle = new Bundle();
        queryBundle.putString(BundleKeys.SEARCH_QUERY_KEY, searchQuery);

        PagerAdapter searchPagerAdapter = new SearchFragmentPagerAdapter(getSupportFragmentManager(), this, queryBundle);
        searchViewPager.setAdapter(searchPagerAdapter);
        searchViewPager.setOffscreenPageLimit(3);
        searchTabLayout.setupWithViewPager(searchViewPager);
    }

    private void setListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        searchQueryEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                search();
                return true;
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
    }

    private void search() {
        searchQuery = "";
        queryBundle.putString(BundleKeys.SEARCH_QUERY_KEY, searchQuery);
        searchQuery = searchQueryEditText.getText().toString();
        queryBundle.putString(BundleKeys.SEARCH_QUERY_KEY, searchQuery);
        searchViewPager.getAdapter().notifyDataSetChanged();
    }

}
