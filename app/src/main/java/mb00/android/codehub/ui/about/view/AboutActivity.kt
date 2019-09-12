package mb00.android.codehub.ui.about.view

import android.os.Bundle
import androidx.appcompat.widget.Toolbar

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

        val aboutToolbar = findViewById<Toolbar>(R.id.toolbar_about)
        aboutToolbar.setTitle(R.string.app_name)

        setSupportActionBar(aboutToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
