package mb00.android.codehub.ui.login.viewmodel

import android.content.Context

import mb00.android.codehub.logic.preferences.PreferenceManager
import mb00.android.codehub.ui.base.viewmodel.BaseViewModel
import mb00.android.codehub.ui.login.router.LoginRouter


class LoginViewModel(internal val context: Context, internal val router: LoginRouter, internal val preferenceManager: PreferenceManager) : BaseViewModel() {

    fun loginOAuth() = router.loginOAuth()

}
