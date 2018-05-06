package mb00.android.codehub.ui.repo.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import mb00.android.codehub.R;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.base.view.NavigationDrawerActivity;
import mb00.android.codehub.ui.repo.adapter.RepoAdapter;
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter;

/**
 * Launched from {@link RepoAdapter} if repository in RecyclerView is clicked
 * Immediately launches {@link RepoFragmentPagerAdapter}
 */

public class RepoActivity extends NavigationDrawerActivity {

    //==============================================================================================
    // RepoActivity fields
    //==============================================================================================

    private Bundle repoBundle;

    private ImageButton repoBackButton;
    private String repoFullName;
    private TextView repoToolbarTextView;

    private ViewPager repoViewPager;
    private PagerAdapter repoPagerAdapter;
    private TabLayout repoTabLayout;

    //==============================================================================================
    // Activity / lifecycle methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        super.setupNavigationDrawer(this);

        repoBundle = getIntent().getExtras();

        repoBackButton = findViewById(R.id.repo_back_button);
        repoBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        repoFullName = repoBundle.getString(BundleKeys.REPO_FULL_NAME);

        repoToolbarTextView = findViewById(R.id.repo_toolbar_text_view);
        repoToolbarTextView.setText(repoFullName);

        repoViewPager = findViewById(R.id.repo_view_pager);
        repoPagerAdapter = new RepoFragmentPagerAdapter(getSupportFragmentManager(), this, repoBundle);
        repoViewPager.setAdapter(repoPagerAdapter);
        repoViewPager.setOffscreenPageLimit(9);

        repoTabLayout = findViewById(R.id.repo_tab_layout);
        repoTabLayout.setupWithViewPager(repoViewPager);
    }

}
