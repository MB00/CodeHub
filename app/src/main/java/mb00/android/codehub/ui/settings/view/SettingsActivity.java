package mb00.android.codehub.ui.settings.view;

import android.os.Bundle;

import mb00.android.codehub.R;
import mb00.android.codehub.databinding.ActivitySettingsBinding;
import mb00.android.codehub.ui.base.view.BaseBindingActivity;
import mb00.android.codehub.ui.settings.viewmodel.SettingsViewModel;

/**
 * Launched from Activity's navigation drawer if "Settings" item is clicked
 */

public class SettingsActivity extends BaseBindingActivity<ActivitySettingsBinding, SettingsViewModel> {

    @Override
    protected int layout() {
        return R.layout.activity_settings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolbar();
    }

    private void initToolbar() {
        getBinding().settingsToolbar.setTitle(R.string.settings);
        setSupportActionBar(getBinding().settingsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
