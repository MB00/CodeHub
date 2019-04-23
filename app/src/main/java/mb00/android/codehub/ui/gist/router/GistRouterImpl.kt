package mb00.android.codehub.ui.gist.router

import android.support.v7.app.AppCompatActivity


class GistRouterImpl(private val activity: AppCompatActivity) : GistRouter {

    override fun finishGistActivity() {
        activity.finish()
    }

}