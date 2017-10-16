package android.mb00.codehub.ui.adapter;

import android.content.Context;
import android.mb00.codehub.R;
import android.mb00.codehub.ui.RepoCodeFragment;
import android.mb00.codehub.ui.RepoCommitsFragment;
import android.mb00.codehub.ui.RepoContributorsFragment;
import android.mb00.codehub.ui.RepoIssuesFragment;
import android.mb00.codehub.ui.RepoLicenseFragment;
import android.mb00.codehub.ui.RepoPullRequestsFragment;
import android.mb00.codehub.ui.RepoPulseFragment;
import android.mb00.codehub.ui.RepoReadmeFragment;
import android.mb00.codehub.ui.RepoReleasesFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class RepoFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 9;

    private String readmeTabTitle;
    private String codeTabTitle;
    private String issuesTabTitle;
    private String pullRequestsTabTitle;
    private String pulseTabTitle;
    private String commitsTabTitle;
    private String releasesTabTitle;
    private String contributorsTabTitle;
    private String licenseTabTitle;

    private Bundle repoArgs;

    public RepoFragmentPagerAdapter(FragmentManager fragmentManager, Context context, Bundle repoBundle) {
        super(fragmentManager);

        readmeTabTitle = context.getResources().getString(R.string.readme);
        codeTabTitle = context.getResources().getString(R.string.code);
        issuesTabTitle = context.getResources().getString(R.string.issues);
        pullRequestsTabTitle = context.getResources().getString(R.string.pull_requests);
        pulseTabTitle = context.getResources().getString(R.string.pulse);
        commitsTabTitle = context.getResources().getString(R.string.commits);
        releasesTabTitle = context.getResources().getString(R.string.releases);
        contributorsTabTitle = context.getResources().getString(R.string.contributors);
        licenseTabTitle = context.getResources().getString(R.string.license);

        repoArgs = repoBundle;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                RepoReadmeFragment readmeFragment = new RepoReadmeFragment();
                readmeFragment.setArguments(repoArgs);
                return readmeFragment;
            case 1:
                RepoCodeFragment codeFragment = new RepoCodeFragment();
                codeFragment.setArguments(repoArgs);
                return codeFragment;
            case 2:
                RepoIssuesFragment issuesFragment = new RepoIssuesFragment();
                issuesFragment.setArguments(repoArgs);
                return issuesFragment;
            case 3:
                RepoPullRequestsFragment pullRequestsFragment = new RepoPullRequestsFragment();
                pullRequestsFragment.setArguments(repoArgs);
                return pullRequestsFragment;
            case 4:
                RepoPulseFragment pulseFragment = new RepoPulseFragment();
                pulseFragment.setArguments(repoArgs);
                return pulseFragment;
            case 5:
                RepoCommitsFragment commitsFragment = new RepoCommitsFragment();
                commitsFragment.setArguments(repoArgs);
                return commitsFragment;
            case 6:
                RepoReleasesFragment releasesFragment = new RepoReleasesFragment();
                releasesFragment.setArguments(repoArgs);
                return releasesFragment;
            case 7:
                RepoContributorsFragment contributorsFragment = new RepoContributorsFragment();
                contributorsFragment.setArguments(repoArgs);
                return contributorsFragment;
            case 8:
                RepoLicenseFragment licenseFragment = new RepoLicenseFragment();
                licenseFragment.setArguments(repoArgs);
                return licenseFragment;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return readmeTabTitle;
            case 1:
                return codeTabTitle;
            case 2:
                return issuesTabTitle;
            case 3:
                return pullRequestsTabTitle;
            case 4:
                return pulseTabTitle;
            case 5:
                return commitsTabTitle;
            case 6:
                return releasesTabTitle;
            case 7:
                return contributorsTabTitle;
            case 8:
                return licenseTabTitle;
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
