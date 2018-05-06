package mb00.android.codehub.ui.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;

import mb00.android.codehub.R;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.base.view.NavigationDrawerActivity;
import mb00.android.codehub.ui.home.adapter.HomeFragmentPagerAdapter;
import mb00.android.codehub.ui.main.view.MainActivity;
import mb00.android.codehub.ui.search.view.SearchActivity;

/**
 * Launched from {@link MainActivity} if {@link PreferenceKeys .SIGNED_IN == true}
 */

public class HomeActivity extends NavigationDrawerActivity {

    //==============================================================================================
    // HomeActivity fields
    //==============================================================================================

    private Toolbar homeToolbar;
    private ImageButton menuButton;
    private ImageButton searchButton;

    private ViewPager homeViewPager;
    private PagerAdapter homePagerAdapter;
    private TabLayout homeTabLayout;

    private DrawerLayout navigationDrawer;

    //==============================================================================================
    // Activity / lifecycle methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.setupNavigationDrawer(this);

        homeToolbar = findViewById(R.id.toolbar_home);
        menuButton = findViewById(R.id.home_menu_button);
        searchButton = findViewById(R.id.home_search_button);

        homeViewPager = findViewById(R.id.home_view_pager);
        homePagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), this);
        homeViewPager.setAdapter(homePagerAdapter);
        homeViewPager.setOffscreenPageLimit(4);

        homeTabLayout = findViewById(R.id.home_tab_layout);
        homeTabLayout.setupWithViewPager(homeViewPager);

        navigationDrawer = findViewById(R.id.drawer_layout);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationDrawer.openDrawer(Gravity.LEFT);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}
