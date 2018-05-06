package mb00.android.codehub.ui.settings.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import mb00.android.codehub.R;

/**
 * Launched from Activity's navigation drawer if "Settings" item is clicked
 */

public class SettingsActivity extends AppCompatActivity {

    //==============================================================================================
    // Activity / lifecycle methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar settingsToolbar = findViewById(R.id.toolbar_settings);
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
