package mb00.android.codehub.ui.settings.view

import android.os.Bundle

import mb00.android.codehub.R
import mb00.android.codehub.databinding.ActivitySettingsBinding
import mb00.android.codehub.ui.base.view.BaseBindingActivity
import mb00.android.codehub.ui.settings.viewmodel.SettingsViewModel

/**
 * Launched from Activity's navigation drawer if "Settings" item is clicked
 */

class SettingsActivity : BaseBindingActivity<ActivitySettingsBinding, SettingsViewModel>() {

    override fun layout(): Int {
        return R.layout.activity_settings
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
    }

    private fun initToolbar() {
        binding.settingsToolbar.setTitle(R.string.settings)
        setSupportActionBar(binding.settingsToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
