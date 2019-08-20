package mb00.android.codehub.ui.about.view

import android.os.Bundle
import mb00.android.codehub.R
import mb00.android.codehub.databinding.ActivityAboutBinding
import mb00.android.codehub.ui.about.viewmodel.AboutViewModel
import mb00.android.codehub.ui.base.view.BaseBindingActivity

/**
 * Launched from Activity's navigation drawer if "About" item is clicked
 */

class AboutActivity : BaseBindingActivity<ActivityAboutBinding, AboutViewModel>() {

    override fun layout(): Int {
        return R.layout.activity_about
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.aboutToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
