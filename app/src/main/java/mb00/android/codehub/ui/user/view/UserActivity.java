package mb00.android.codehub.ui.user.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import mb00.android.codehub.R;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.base.view.NavigationDrawerActivity;
import mb00.android.codehub.ui.user.adapter.UserAdapter;
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter;

/**
 * Launched from {@link UserAdapter} if user in RecyclerView is clicked
 * Immediately launches {@link UserFragmentPagerAdapter}
 */

public class UserActivity extends NavigationDrawerActivity {

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
        super.setupNavigationDrawer(this);

        userBundle = getIntent().getExtras();

        userBackButton = findViewById(R.id.user_back_button);
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

        userToolbarTextView = findViewById(R.id.user_toolbar_text_view);
        userToolbarTextView.setText(userName);

        userViewPager = findViewById(R.id.user_view_pager);
        userPagerAdapter = new UserFragmentPagerAdapter(getSupportFragmentManager(), this, userBundle);
        userViewPager.setAdapter(userPagerAdapter);
        userViewPager.setOffscreenPageLimit(7);

        userTabLayout = findViewById(R.id.user_tab_layout);
        userTabLayout.setupWithViewPager(userViewPager);

        userViewPager.setCurrentItem(userBundle.getInt(BundleKeys.VIEW_PAGER_POSITION));
    }

}
