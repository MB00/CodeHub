package mb00.android.codehub.ui;

import mb00.android.codehub.R;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.adapter.UserAdapter;
import mb00.android.codehub.ui.adapter.UserFragmentPagerAdapter;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Launched from {@link UserAdapter} if user in RecyclerView is clicked
 * Immediately launches {@link UserFragmentPagerAdapter}
 */

public class UserActivity extends AppCompatActivity {

    //==============================================================================================
    // UserActivity fields
    //==============================================================================================

    private Bundle userBundle;

    private ImageButton userBackButton;
    private String userName;
    private TextView userToolbarTextView;

    private ViewPager userViewPager;
    private PagerAdapter userPagerAdapter;
    private TabLayout userTabLayout;

    //==============================================================================================
    // Activity / lifecycle methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userBundle = getIntent().getExtras();

        userBackButton = (ImageButton) findViewById(R.id.user_back_button);
        userBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        userName = userBundle.getString(BundleKeys.USER_NAME);
        if (userName == null) {
            userName = getSharedPreferences(PreferenceKeys.PREFERENCES, MODE_PRIVATE)
                    .getString(PreferenceKeys.USER_NAME, "");
        }

        userToolbarTextView = (TextView) findViewById(R.id.user_toolbar_text_view);
        userToolbarTextView.setText(userName);

        userViewPager = (ViewPager) findViewById(R.id.user_view_pager);
        userPagerAdapter = new UserFragmentPagerAdapter(getSupportFragmentManager(), this, userBundle);
        userViewPager.setAdapter(userPagerAdapter);
        userViewPager.setOffscreenPageLimit(7);

        userTabLayout = (TabLayout) findViewById(R.id.user_tab_layout);
        userTabLayout.setupWithViewPager(userViewPager);

        userViewPager.setCurrentItem(userBundle.getInt(BundleKeys.VIEW_PAGER_POSITION));

        NavigationDrawerSetup.setupNavigationDrawer(UserActivity.this);
    }

}
