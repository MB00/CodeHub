package mb00.android.codehub.ui.login.view

import android.os.Bundle
import mb00.android.codehub.R
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.ActivityLoginBinding
import mb00.android.codehub.ui.base.view.BaseBindingActivity
import mb00.android.codehub.ui.home.view.HomeActivity
import mb00.android.codehub.ui.login.viewmodel.LoginViewModel
import mb00.android.codehub.ui.main.view.MainActivity

/**
 * Launched from [MainActivity] if [.SIGNED_IN == false][PreferenceKeys]
 * Once logged in, PreferenceKeys.SIGNED_IN is set to true, and [HomeActivity] is launched
 */

class LoginActivity : BaseBindingActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun layout(): Int {
        return R.layout.activity_login
    }

    override fun onBackPressed() {
        finishAffinity()
    }

}
