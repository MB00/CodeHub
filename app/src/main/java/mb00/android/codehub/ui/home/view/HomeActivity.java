package mb00.android.codehub.ui.home.view;

import android.os.Bundle;

import mb00.android.codehub.R;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.databinding.ActivityHomeBinding;
import mb00.android.codehub.ui.base.view.BaseDrawerActivity;
import mb00.android.codehub.ui.home.adapter.HomeFragmentPagerAdapter;
import mb00.android.codehub.ui.home.viewmodel.HomeViewModel;
import mb00.android.codehub.ui.main.view.MainActivity;

/**
 * Launched from {@link MainActivity} if {@link PreferenceKeys .SIGNED_IN == true}
 */

public class HomeActivity extends BaseDrawerActivity<ActivityHomeBinding, HomeViewModel> {

    @Override
    protected int layout() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewPager();
    }

    private void initViewPager() {
        HomeFragmentPagerAdapter homePagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), this);

        getBinding().homeViewPager.setAdapter(homePagerAdapter);
        getBinding().homeViewPager.setOffscreenPageLimit(4);
        getBinding().homeTabLayout.setupWithViewPager(getBinding().homeViewPager);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}
