package mb00.android.codehub.ui.search.router

import android.support.v7.app.AppCompatActivity

class SearchRouterImpl(private val activity: AppCompatActivity) : SearchRouter {

    override fun loadPrevious() {
        activity.finish()
    }
}
