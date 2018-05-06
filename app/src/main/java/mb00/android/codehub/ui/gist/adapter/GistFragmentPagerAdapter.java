package mb00.android.codehub.ui.gist.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mb00.android.codehub.R;
import mb00.android.codehub.ui.gist.view.GistActivity;
import mb00.android.codehub.ui.gist.view.GistCommentsFragment;
import mb00.android.codehub.ui.gist.view.GistFilesFragment;

/**
 * FragmentPagerAdapter used to display gist-related Fragments; launched from {@link GistActivity}
 */

public class GistFragmentPagerAdapter extends FragmentPagerAdapter {

    //==============================================================================================
    // GistFragmentPagerAdapter fields
    //==============================================================================================

    private final int PAGE_COUNT = 2;

    private String filesTabTitle;
    private String commentsTabTitle;

    private Bundle gistArgs;

    //==============================================================================================
    // GistFileAdapter constructor
    //==============================================================================================

    public GistFragmentPagerAdapter(FragmentManager fragmentManager, Context context, Bundle gistBundle) {
        super(fragmentManager);

        filesTabTitle = context.getResources().getString(R.string.files);
        commentsTabTitle = context.getResources().getString(R.string.comments);

        gistArgs = gistBundle;
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
                GistFilesFragment filesFragment = new GistFilesFragment();
                filesFragment.setArguments(gistArgs);
                return filesFragment;
            case 1:
                GistCommentsFragment commentsFragment = new GistCommentsFragment();
                commentsFragment.setArguments(gistArgs);
                return commentsFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return filesTabTitle;
            case 1:
                return commentsTabTitle;
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
