package mb00.android.codehub.ui.home.interactor

import io.reactivex.Single
import mb00.android.codehub.api.model.Pulse
import mb00.android.codehub.api.model.Repo
import mb00.android.codehub.api.model.User


interface HomeInteractor {
    fun openNavigationDrawer()
    fun loadUserFollowers(header: String, user: String): Single<List<User>>
    fun loadUserFollowing(header: String, user: String): Single<List<User>>
    fun loadUserPulse(header: String, user: String): Single<List<Pulse>>
    fun loadUserRepos(header: String, user: String): Single<List<Repo>>
}
