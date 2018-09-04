package mb00.android.codehub.ui.repo.view;

import android.os.Bundle;

import mb00.android.codehub.R;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.databinding.ActivityRepoBinding;
import mb00.android.codehub.ui.base.view.BaseDrawerActivity;
import mb00.android.codehub.ui.repo.adapter.RepoAdapter;
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter;
import mb00.android.codehub.ui.repo.viewmodel.RepoViewModel;

/**
 * Launched from {@link RepoAdapter} if repository in RecyclerView is clicked
 * Immediately launches {@link RepoFragmentPagerAdapter}
 */

public class RepoActivity extends BaseDrawerActivity<ActivityRepoBinding, RepoViewModel> {

    private Bundle repoBundle;
    private String repoFullName;

    @Override
    protected int layout() {
        return R.layout.activity_repo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getExtras();
        initToolbar();
        initViewPager();
    }

    private void getExtras() {
        repoBundle = getIntent().getExtras();
        repoFullName = repoBundle.getString(BundleKeys.REPO_FULL_NAME);
    }

    private void initToolbar() {
        getBinding().repoToolbarTextView.setText(repoFullName);
    }

    private void initViewPager() {
        RepoFragmentPagerAdapter repoPagerAdapter = new RepoFragmentPagerAdapter(getSupportFragmentManager(), this, repoBundle);
        getBinding().repoViewPager.setAdapter(repoPagerAdapter);
        getBinding().repoViewPager.setOffscreenPageLimit(9);
        getBinding().repoViewPager.setPageMargin(4);
        getBinding().repoViewPager.setPageMarginDrawable(R.color.grey);
        getBinding().repoTabLayout.setupWithViewPager(getBinding().repoViewPager);
    }

}
