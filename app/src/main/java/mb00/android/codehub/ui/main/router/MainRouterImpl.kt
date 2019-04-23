package mb00.android.codehub.ui.main.router

import android.content.Intent
import android.support.v7.app.AppCompatActivity

import mb00.android.codehub.ui.home.view.HomeActivity
import mb00.android.codehub.ui.login.view.LoginActivity

class MainRouterImpl(private val activity: AppCompatActivity) : MainRouter {

    override fun loadHomeActivity() {
        val i = Intent(activity, HomeActivity::class.java)
        activity.startActivity(i)
    }

    override fun loadLoginActivity() {
        val i = Intent(activity, LoginActivity::class.java)
        activity.startActivity(i)
    }

}
