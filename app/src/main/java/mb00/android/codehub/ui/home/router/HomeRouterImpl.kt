package mb00.android.codehub.ui.home.router

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import mb00.android.codehub.ui.search.view.SearchActivity


class HomeRouterImpl(private val activity: AppCompatActivity) : HomeRouter {

    override fun loadSearchActivity() {
        val i = Intent(activity, SearchActivity::class.java)
        activity.startActivity(i)
    }

}
