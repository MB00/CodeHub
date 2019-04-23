package mb00.android.codehub.ui.login.router

import android.content.Intent
import android.support.v7.app.AppCompatActivity

import mb00.android.codehub.ui.home.view.HomeActivity


class LoginRouterImpl(private val activity: AppCompatActivity) : LoginRouter {

    override fun loadHomeActivity() {
        val homeActivityIntent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(homeActivityIntent)
    }

}
