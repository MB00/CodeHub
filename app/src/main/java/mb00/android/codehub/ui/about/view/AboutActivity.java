package mb00.android.codehub.ui.about.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import mb00.android.codehub.R;

/**
 * Launched from Activity's navigation drawer if "About" item is clicked
 */

public class AboutActivity extends AppCompatActivity {

    //==============================================================================================
    // Activity / lifecycle methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar aboutToolbar = findViewById(R.id.toolbar_about);
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
