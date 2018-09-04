package mb00.android.codehub.ui.gist.view;

import android.os.Bundle;

import mb00.android.codehub.R;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.databinding.ActivityGistBinding;
import mb00.android.codehub.ui.base.view.BaseDrawerActivity;
import mb00.android.codehub.ui.gist.adapter.GistAdapter;
import mb00.android.codehub.ui.gist.adapter.GistFragmentPagerAdapter;
import mb00.android.codehub.ui.gist.viewmodel.GistViewModel;

/**
 * Launched from {@link GistAdapter} if gist in RecyclerView is clicked
 * Immediately launches {@link GistFragmentPagerAdapter}
 */

public class GistActivity extends BaseDrawerActivity<ActivityGistBinding, GistViewModel> {

    private Bundle gistBundle;
    private String userName;

    @Override
    protected int layout() {
        return R.layout.activity_gist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getExtras();
        initToolbar();
        initViewPager();
    }

    private void getExtras() {
        gistBundle = getIntent().getExtras();
        userName = gistBundle.getString(BundleKeys.USER_NAME);
    }

    private void initToolbar() {
        String gistToolbarText = userName + "/gist";
        getBinding().gistToolbarTextView.setText(gistToolbarText);
    }

    private void initViewPager() {
        GistFragmentPagerAdapter gistPagerAdapter = new GistFragmentPagerAdapter(getSupportFragmentManager(), this, gistBundle);

        getBinding().gistViewPager.setAdapter(gistPagerAdapter);
        getBinding().gistViewPager.setOffscreenPageLimit(2);
        getBinding().gistTabLayout.setupWithViewPager(getBinding().gistViewPager);
    }

}
