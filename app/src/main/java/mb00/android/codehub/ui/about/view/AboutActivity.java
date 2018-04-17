package mb00.android.codehub.ui.about.view;

import mb00.android.codehub.R;
import mb00.android.codehub.ui.NavigationDrawerSetup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Launched from {@link NavigationDrawerSetup} if "About" item is clicked
 */

public class AboutActivity extends AppCompatActivity {

    //==============================================================================================
    // Activity / lifecycle methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar aboutToolbar = (Toolbar) findViewById(R.id.toolbar_about);
        aboutToolbar.setTitle(R.string.app_name);

        setSupportActionBar(aboutToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
