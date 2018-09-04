package mb00.android.codehub.ui.about.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import mb00.android.codehub.R;
import mb00.android.codehub.databinding.ActivityAboutBinding;
import mb00.android.codehub.ui.about.viewmodel.AboutViewModel;
import mb00.android.codehub.ui.base.view.BaseBindingActivity;

/**
 * Launched from Activity's navigation drawer if "About" item is clicked
 */

public class AboutActivity extends BaseBindingActivity<ActivityAboutBinding, AboutViewModel> {

    @Override
    protected int layout() {
        return R.layout.activity_about;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
