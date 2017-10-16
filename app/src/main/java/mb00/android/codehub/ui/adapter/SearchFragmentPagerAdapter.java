package mb00.android.codehub.ui.adapter;

import android.content.Context;

import mb00.android.codehub.ui.SearchIssuesFragment;
import mb00.android.codehub.ui.SearchReposFragment;
import mb00.android.codehub.ui.SearchUsersFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class SearchFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;

    private String reposTabTitle;
    private String usersTabTitle;
    private String issuesTabTitle;

    private Bundle queryArgs;

    public SearchFragmentPagerAdapter(FragmentManager fragmentManager, Context context, Bundle queryArgs) {
        super(fragmentManager);
        reposTabTitle = context.getResources().getString(mb00.android.codehub.R.string.repositories);
        usersTabTitle = context.getResources().getString(mb00.android.codehub.R.string.users);
        issuesTabTitle = context.getResources().getString(mb00.android.codehub.R.string.issues);
        this.queryArgs = queryArgs;
    }

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
