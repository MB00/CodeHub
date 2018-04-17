package mb00.android.codehub.ui.search.adapter;

import mb00.android.codehub.R;
import mb00.android.codehub.ui.search.view.SearchActivity;
import mb00.android.codehub.ui.search.view.SearchIssuesFragment;
import mb00.android.codehub.ui.search.view.SearchReposFragment;
import mb00.android.codehub.ui.search.view.SearchUsersFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * FragmentPagerAdapter used to display search-related Fragments; launched from {@link SearchActivity}
 */

public class SearchFragmentPagerAdapter extends FragmentPagerAdapter {

    //==============================================================================================
    // SearchFragmentPagerAdapter fields
    //==============================================================================================

    private final int PAGE_COUNT = 3;

    private String reposTabTitle;
    private String usersTabTitle;
    private String issuesTabTitle;

    private Bundle queryArgs;

    //==============================================================================================
    // SearchFragmentPagerAdapter constructor
    //==============================================================================================

    public SearchFragmentPagerAdapter(FragmentManager fragmentManager, Context context, Bundle queryArgs) {
        super(fragmentManager);
        reposTabTitle = context.getResources().getString(R.string.repositories);
        usersTabTitle = context.getResources().getString(R.string.users);
        issuesTabTitle = context.getResources().getString(R.string.issues);
        this.queryArgs = queryArgs;
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
                SearchReposFragment searchReposFragment = new SearchReposFragment();
                searchReposFragment.setArguments(queryArgs);
                return searchReposFragment;
            case 1:
                SearchUsersFragment searchUsersFragment = new SearchUsersFragment();
                searchUsersFragment.setArguments(queryArgs);
                return searchUsersFragment;
            case 2:
                SearchIssuesFragment searchIssuesFragment = new SearchIssuesFragment();
                searchIssuesFragment.setArguments(queryArgs);
                return searchIssuesFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return reposTabTitle;
            case 1:
                return usersTabTitle;
            case 2:
                return issuesTabTitle;
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
