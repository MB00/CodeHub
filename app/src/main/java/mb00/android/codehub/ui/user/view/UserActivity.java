package mb00.android.codehub.ui.user.view;

import android.os.Bundle;

import mb00.android.codehub.R;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.databinding.ActivityUserBinding;
import mb00.android.codehub.ui.base.view.BaseDrawerActivity;
import mb00.android.codehub.ui.user.adapter.UserAdapter;
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter;
import mb00.android.codehub.ui.user.viewmodel.UserViewModel;

/**
 * Launched from {@link UserAdapter} if user in RecyclerView is clicked
 * Immediately launches {@link UserFragmentPagerAdapter}
 */

public class UserActivity extends BaseDrawerActivity<ActivityUserBinding, UserViewModel> {

    private Bundle userBundle;
    private String userName;

    @Override
    protected int layout() {
        return R.layout.activity_user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getExtras();
        initToolbar();
        initViewPager();
    }

    private void getExtras() {
        userBundle = getIntent().getExtras();
        userName = userBundle.getString(BundleKeys.USER_NAME);

        if (userName == null) {
            userName = getSharedPreferences(PreferenceKeys.PREFERENCES, MODE_PRIVATE)
                    .getString(PreferenceKeys.USER_NAME, "");
        }
    }

    private void initToolbar() {
        getBinding().userToolbarTextView.setText(userName);
    }

    private void initViewPager() {
        UserFragmentPagerAdapter userPagerAdapter = new UserFragmentPagerAdapter(getSupportFragmentManager(), this, userBundle);

        getBinding().userViewPager.setAdapter(userPagerAdapter);
        getBinding().userViewPager.setOffscreenPageLimit(7);
        getBinding().userViewPager.setPageMargin(4);
        getBinding().userViewPager.setPageMarginDrawable(R.color.grey);
        getBinding().userTabLayout.setupWithViewPager(getBinding().userViewPager);
        getBinding().userViewPager.setCurrentItem(userBundle.getInt(BundleKeys.VIEW_PAGER_POSITION));
    }

}
