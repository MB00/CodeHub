package mb00.android.codehub.ui;

import mb00.android.codehub.R;
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

    private ImageButton gistBackButton;
    private TextView gistToolbarTextView;

    private ViewPager gistViewPager;
    private PagerAdapter gistPagerAdapter;
    private TabLayout gistTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gist);

        gistBundle = new Bundle();

        gistBackButton = (ImageButton) findViewById(R.id.gist_back_button);
        gistBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        gistToolbarTextView = (TextView) findViewById(R.id.gist_toolbar_text_view);

        gistViewPager = (ViewPager) findViewById(R.id.gist_view_pager);
        gistPagerAdapter = new GistFragmentPagerAdapter(getSupportFragmentManager(), this, gistBundle);
        gistViewPager.setAdapter(gistPagerAdapter);

        gistTabLayout = (TabLayout) findViewById(R.id.gist_tab_layout);
        gistTabLayout.setupWithViewPager(gistViewPager);

        NavigationDrawerSetup.setupNavigationDrawer(GistActivity.this);
    }

}
