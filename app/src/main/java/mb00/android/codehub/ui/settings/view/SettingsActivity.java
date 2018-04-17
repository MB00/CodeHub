package mb00.android.codehub.ui.settings.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import mb00.android.codehub.R;
import mb00.android.codehub.ui.NavigationDrawerSetup;

/**
 * Launched from {@link NavigationDrawerSetup} if "Settings" item is clicked
 */

public class SettingsActivity extends AppCompatActivity {

    //==============================================================================================
    // Activity / lifecycle methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar settingsToolbar = (Toolbar) findViewById(R.id.toolbar_settings);
        settingsToolbar.setTitle(R.string.settings);
        setSupportActionBar(settingsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
