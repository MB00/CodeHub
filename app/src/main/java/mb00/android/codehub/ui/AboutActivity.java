package mb00.android.codehub.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class AboutActivity extends AppCompatActivity {

    private Toolbar aboutToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mb00.android.codehub.R.layout.activity_about);

        aboutToolbar = (Toolbar) findViewById(mb00.android.codehub.R.id.toolbar_about);
        aboutToolbar.setTitle(mb00.android.codehub.R.string.app_name);
        setSupportActionBar(aboutToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
