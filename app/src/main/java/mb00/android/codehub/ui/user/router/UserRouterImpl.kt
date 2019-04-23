package mb00.android.codehub.ui.user.router

import android.support.v7.app.AppCompatActivity


class UserRouterImpl(private val activity: AppCompatActivity) : UserRouter {

    override fun finishUserActivity() {
        activity.finish()
    }

}
