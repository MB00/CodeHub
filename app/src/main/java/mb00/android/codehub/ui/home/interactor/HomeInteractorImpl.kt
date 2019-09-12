package mb00.android.codehub.ui.home.interactor

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

import io.reactivex.Single
import mb00.android.codehub.R
import mb00.android.codehub.api.manager.ApiCallManager
import mb00.android.codehub.api.model.Pulse
import mb00.android.codehub.api.model.Repo
import mb00.android.codehub.api.model.User


class HomeInteractorImpl(
        private val activity: AppCompatActivity,
        private val apiCallManager: ApiCallManager
) : HomeInteractor {

    override fun openNavigationDrawer() {
        val navigationDrawer = activity.window.decorView.rootView.findViewById<DrawerLayout>(R.id.drawer_layout)
        navigationDrawer.openDrawer(GravityCompat.START)
    }

    override fun loadUserFollowers(header: String, user: String): Single<List<User>> {
        return apiCallManager.loadUserFollowers(header, user)
    }

    override fun loadUserFollowing(header: String, user: String): Single<List<User>> {
        return apiCallManager.loadUserFollowing(header, user)
    }

    override fun loadUserPulse(header: String, user: String): Single<List<Pulse>> {
        return apiCallManager.loadUserPulse(header, user)
    }

    override fun loadUserRepos(header: String, user: String): Single<List<Repo>> {
        return apiCallManager.loadUserRepos(header, user)
    }

}
