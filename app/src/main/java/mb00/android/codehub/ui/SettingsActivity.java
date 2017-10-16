package mb00.android.codehub.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mb00.android.codehub.R.layout.activity_settings);

        Toolbar settingsToolbar = (Toolbar) findViewById(mb00.android.codehub.R.id.toolbar_settings);
        settingsToolbar.setTitle(mb00.android.codehub.R.string.settings);
        setSupportActionBar(settingsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
