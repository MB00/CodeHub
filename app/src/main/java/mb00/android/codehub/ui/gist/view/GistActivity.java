package mb00.android.codehub.ui.gist.view;

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
import mb00.android.codehub.ui.gist.adapter.GistAdapter;
import mb00.android.codehub.ui.gist.adapter.GistFragmentPagerAdapter;

/**
 * Launched from {@link GistAdapter} if gist in RecyclerView is clicked
 * Immediately launches {@link GistFragmentPagerAdapter}
 */

public class GistActivity extends NavigationDrawerActivity {

    //==============================================================================================
    // GistActivity fields
    //==============================================================================================

    private Bundle gistBundle;
    private String userName;
    private String gistToolbarText;

    private ImageButton gistBackButton;
    private TextView gistToolbarTextView;

    private ViewPager gistViewPager;
    private PagerAdapter gistPagerAdapter;
    private TabLayout gistTabLayout;

    //==============================================================================================
    // Activity / lifecycle methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gist);
        super.setupNavigationDrawer(this);

        gistBundle = getIntent().getExtras();
        userName = gistBundle.getString(BundleKeys.USER_NAME);

        gistBackButton = findViewById(R.id.gist_back_button);
        gistToolbarTextView = findViewById(R.id.gist_toolbar_text_view);
        gistToolbarText = userName + "/gist";
        gistViewPager = findViewById(R.id.gist_view_pager);
        gistPagerAdapter = new GistFragmentPagerAdapter(getSupportFragmentManager(), this, gistBundle);
        gistTabLayout = findViewById(R.id.gist_tab_layout);

        gistBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gistToolbarTextView.setText(gistToolbarText);
        gistViewPager.setAdapter(gistPagerAdapter);
        gistViewPager.setOffscreenPageLimit(2);
        gistTabLayout.setupWithViewPager(gistViewPager);
    }

}
