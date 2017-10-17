package mb00.android.codehub.ui.adapter;

import mb00.android.codehub.ui.UserFollowersFragment;
import mb00.android.codehub.ui.UserFollowingFragment;
import mb00.android.codehub.ui.UserGistsFragment;
import mb00.android.codehub.ui.UserOverviewFragment;
import mb00.android.codehub.ui.UserPulseFragment;
import mb00.android.codehub.ui.UserReposFragment;
import mb00.android.codehub.ui.UserStarredFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class UserFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 7;

    private String overviewTabTitle;
    private String reposTabTitle;
    private String starredTabTitle;
    private String gistsTabTitle;
    private String pulseTabTitle;
    private String followersTabTitle;
    private String followingTabTitle;

    private Bundle userArgs;

    public UserFragmentPagerAdapter(FragmentManager fragmentManager, Context context, Bundle userBundle) {
        super(fragmentManager);

        overviewTabTitle = context.getResources().getString(mb00.android.codehub.R.string.overview);
        reposTabTitle = context.getResources().getString(mb00.android.codehub.R.string.repositories);
        starredTabTitle = context.getResources().getString(mb00.android.codehub.R.string.starred);
        gistsTabTitle = context.getResources().getString(mb00.android.codehub.R.string.gists);
        pulseTabTitle = context.getResources().getString(mb00.android.codehub.R.string.pulse);
        followersTabTitle = context.getResources().getString(mb00.android.codehub.R.string.followers);
        followingTabTitle = context.getResources().getString(mb00.android.codehub.R.string.following);

        userArgs = userBundle;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                UserOverviewFragment overviewFragment = new UserOverviewFragment();
                overviewFragment.setArguments(userArgs);
                return overviewFragment;
            case 1:
                UserReposFragment reposFragment = new UserReposFragment();
                reposFragment.setArguments(userArgs);
                return reposFragment;
            case 2:
                UserStarredFragment starredFragment = new UserStarredFragment();
                starredFragment.setArguments(userArgs);
                return starredFragment;
            case 3:
                UserGistsFragment gistsFragment = new UserGistsFragment();
                gistsFragment.setArguments(userArgs);
                return gistsFragment;
            case 4:
                UserPulseFragment pulseFragment = new UserPulseFragment();
                pulseFragment.setArguments(userArgs);
                return pulseFragment;
            case 5:
                UserFollowersFragment followersFragment = new UserFollowersFragment();
                followersFragment.setArguments(userArgs);
                return followersFragment;
            case 6:
                UserFollowingFragment followingFragment = new UserFollowingFragment();
                followingFragment.setArguments(userArgs);
                return followingFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return overviewTabTitle;
            case 1:
                return reposTabTitle;
            case 2:
                return starredTabTitle;
            case 3:
                return gistsTabTitle;
            case 4:
                return pulseTabTitle;
            case 5:
                return followersTabTitle;
            case 6:
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
