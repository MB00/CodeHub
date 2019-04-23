package mb00.android.codehub.ui.main.view

import android.os.Bundle
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.logic.preferences.PreferenceManager
import mb00.android.codehub.ui.base.view.BaseActivity
import mb00.android.codehub.ui.home.view.HomeActivity
import mb00.android.codehub.ui.login.view.LoginActivity
import mb00.android.codehub.ui.main.router.MainRouter
import javax.inject.Inject

/**
 * Program execution starts here
 * Launches [HomeActivity] if [.SIGNED_IN == true][PreferenceKeys]
 * Launches [LoginActivity] otherwise
 */

class MainActivity : BaseActivity() {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    @Inject
    lateinit var router: MainRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (preferenceManager.isSignedIn)
            router.loadHomeActivity()
        else
            router.loadLoginActivity()
    }

}
