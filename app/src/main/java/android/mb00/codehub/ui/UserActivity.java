package android.mb00.codehub.ui;

import android.mb00.codehub.R;
import android.mb00.codehub.data.BundleKeys;
import android.mb00.codehub.data.PreferenceKeys;
import android.mb00.codehub.ui.adapter.UserFragmentPagerAdapter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class UserActivity extends AppCompatActivity {

    private Bundle userBundle;

    private Toolbar userToolbar;
    private ImageButton userBackButton;
    private String userName;
    private TextView userToolbarTextView;

    private ViewPager userViewPager;
    private PagerAdapter userPagerAdapter;
    private TabLayout userTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userBundle = getIntent().getExtras();

        userToolbar = (Toolbar) findViewById(R.id.toolbar_user);
        setSupportActionBar(userToolbar);

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

        userTabLayout = (TabLayout) findViewById(R.id.user_tab_layout);
        userTabLayout.setupWithViewPager(userViewPager);

        userViewPager.setCurrentItem(userBundle.getInt(BundleKeys.VIEW_PAGER_POSITION));

        NavigationDrawerSetup.setupNavigationDrawer(UserActivity.this);
    }

}
