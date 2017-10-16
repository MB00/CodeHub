package android.mb00.codehub.ui;

import android.mb00.codehub.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class AboutActivity extends AppCompatActivity {

    private Toolbar aboutToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutToolbar = (Toolbar) findViewById(R.id.toolbar_about);
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
