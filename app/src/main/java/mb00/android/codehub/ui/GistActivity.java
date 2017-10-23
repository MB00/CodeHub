package mb00.android.codehub.ui;

import mb00.android.codehub.R;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.ui.adapter.GistFragmentPagerAdapter;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class GistActivity extends AppCompatActivity {

    private Bundle gistBundle;
    private String userName;
    private String gistToolbarText;

    private ImageButton gistBackButton;
    private TextView gistToolbarTextView;

    private ViewPager gistViewPager;
    private PagerAdapter gistPagerAdapter;
    private TabLayout gistTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gist);

        gistBundle = getIntent().getExtras();
        userName = gistBundle.getString(BundleKeys.USER_NAME);

        gistBackButton = (ImageButton) findViewById(R.id.gist_back_button);
        gistToolbarTextView = (TextView) findViewById(R.id.gist_toolbar_text_view);
        gistToolbarText = userName + "/gist";
        gistViewPager = (ViewPager) findViewById(R.id.gist_view_pager);
        gistPagerAdapter = new GistFragmentPagerAdapter(getSupportFragmentManager(), this, gistBundle);
        gistTabLayout = (TabLayout) findViewById(R.id.gist_tab_layout);

        gistBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gistToolbarTextView.setText(gistToolbarText);
        gistViewPager.setAdapter(gistPagerAdapter);
        gistTabLayout.setupWithViewPager(gistViewPager);
        NavigationDrawerSetup.setupNavigationDrawer(GistActivity.this);
    }

}
