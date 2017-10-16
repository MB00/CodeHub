package mb00.android.codehub.ui;

import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.adapter.RepoFragmentPagerAdapter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class RepoActivity extends AppCompatActivity {

    private Bundle repoBundle;

    private ImageButton repoBackButton;
    private String repoFullName;
    private TextView repoToolbarTextView;

    private ViewPager repoViewPager;
    private PagerAdapter repoPagerAdapter;
    private TabLayout repoTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mb00.android.codehub.R.layout.activity_repo);

        repoBundle = getIntent().getExtras();

        repoBackButton = (ImageButton) findViewById(mb00.android.codehub.R.id.repo_back_button);
        repoBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        repoFullName = repoBundle.getString(BundleKeys.REPO_FULL_NAME);

        repoToolbarTextView = (TextView) findViewById(mb00.android.codehub.R.id.repo_toolbar_text_view);
        repoToolbarTextView.setText(repoFullName);

        repoViewPager = (ViewPager) findViewById(mb00.android.codehub.R.id.repo_view_pager);
        repoPagerAdapter = new RepoFragmentPagerAdapter(getSupportFragmentManager(), this, repoBundle);
        repoViewPager.setAdapter(repoPagerAdapter);

        repoTabLayout = (TabLayout) findViewById(mb00.android.codehub.R.id.repo_tab_layout);
        repoTabLayout.setupWithViewPager(repoViewPager);

        NavigationDrawerSetup.setupNavigationDrawer(RepoActivity.this);
    }

}
