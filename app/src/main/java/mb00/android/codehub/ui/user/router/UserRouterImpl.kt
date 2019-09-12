package mb00.android.codehub.ui.user.router

import androidx.appcompat.app.AppCompatActivity


class UserRouterImpl(private val activity: AppCompatActivity) : UserRouter {

    override fun finishUserActivity() {
        activity.finish()
    }

}
