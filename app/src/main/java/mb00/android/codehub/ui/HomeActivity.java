package mb00.android.codehub.ui;

import android.content.Intent;

import mb00.android.codehub.ui.adapter.HomeFragmentPagerAdapter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;


public class HomeActivity extends AppCompatActivity {

    private Toolbar homeToolbar;
    private ImageButton menuButton;
    private ImageButton searchButton;

    private ViewPager homeViewPager;
    private PagerAdapter homePagerAdapter;
    private TabLayout homeTabLayout;

    private DrawerLayout navigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mb00.android.codehub.R.layout.activity_home);

        homeToolbar = (Toolbar) findViewById(mb00.android.codehub.R.id.toolbar_home);
        menuButton = (ImageButton) findViewById(mb00.android.codehub.R.id.home_menu_button);
        searchButton = (ImageButton) findViewById(mb00.android.codehub.R.id.home_search_button);

        homeViewPager = (ViewPager) findViewById(mb00.android.codehub.R.id.home_view_pager);
        homePagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), this);
        homeViewPager.setAdapter(homePagerAdapter);

        homeTabLayout = (TabLayout) findViewById(mb00.android.codehub.R.id.home_tab_layout);
        homeTabLayout.setupWithViewPager(homeViewPager);

        navigationDrawer = (DrawerLayout) findViewById(mb00.android.codehub.R.id.drawer_layout);

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

        NavigationDrawerSetup.setupNavigationDrawer(HomeActivity.this);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}
