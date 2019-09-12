package mb00.android.codehub.ui.gist.router

import androidx.appcompat.app.AppCompatActivity


class GistRouterImpl(private val activity: AppCompatActivity) : GistRouter {

    override fun finishGistActivity() {
        activity.finish()
    }

}