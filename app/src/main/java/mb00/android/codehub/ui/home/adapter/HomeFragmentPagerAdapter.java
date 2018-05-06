package mb00.android.codehub.ui.home.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mb00.android.codehub.R;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.home.view.HomeActivity;
import mb00.android.codehub.ui.user.view.UserFollowersFragment;
import mb00.android.codehub.ui.user.view.UserFollowingFragment;
import mb00.android.codehub.ui.user.view.UserPulseFragment;
import mb00.android.codehub.ui.user.view.UserReposFragment;

/**
 * FragmentPagerAdapter used to display home-related Fragments; launched from {@link HomeActivity}
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    //==============================================================================================
    // HomeFragmentPagerAdapter fields
    //==============================================================================================

    private final int PAGE_COUNT = 4;

    private String pulseTabTitle;
    private String reposTabTitle;
    private String followersTabTitle;
    private String followingTabTitle;

    private Bundle homeArgs;
    private String userLogin;

    //==============================================================================================
    // HomeFragmentPagerAdapter constructor
    //==============================================================================================

    public HomeFragmentPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);

        pulseTabTitle = context.getResources().getString(R.string.pulse);
        reposTabTitle = context.getResources().getString(R.string.repositories);
        followersTabTitle = context.getResources().getString(R.string.followers);
        followingTabTitle = context.getResources().getString(R.string.following);

        homeArgs = new Bundle();
        userLogin = context.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE).getString(PreferenceKeys.USER_NAME, "");
        homeArgs.putString(BundleKeys.USER_NAME, userLogin);
    }

    //==============================================================================================
    // FragmentPagerAdapter methods
    //==============================================================================================

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                UserPulseFragment pulseFragment = new UserPulseFragment();
                pulseFragment.setArguments(homeArgs);
                return pulseFragment;
            case 1:
                UserReposFragment reposFragment = new UserReposFragment();
                reposFragment.setArguments(homeArgs);
                return reposFragment;
            case 2:
                UserFollowersFragment followersFragment = new UserFollowersFragment();
                followersFragment.setArguments(homeArgs);
                return followersFragment;
            case 3:
                UserFollowingFragment followingFragment = new UserFollowingFragment();
                followingFragment.setArguments(homeArgs);
                return followingFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return pulseTabTitle;
            case 1:
                return reposTabTitle;
            case 2:
                return followersTabTitle;
            case 3:
                return followingTabTitle;
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
