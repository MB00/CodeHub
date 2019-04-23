package mb00.android.codehub.ui.user.interactor

import io.reactivex.Single
import mb00.android.codehub.api.model.Gist
import mb00.android.codehub.api.model.Pulse
import mb00.android.codehub.api.model.Repo
import mb00.android.codehub.api.model.User


interface UserInteractor {
    fun loadUserOverview(header: String, user: String): Single<User>
    fun loadUserPulse(header: String, user: String): Single<List<Pulse>>
    fun loadUserRepos(header: String, user: String): Single<List<Repo>>
    fun loadUserGists(header: String, user: String): Single<List<Gist>>
    fun loadUserStarred(header: String, user: String): Single<List<Repo>>
    fun loadUserFollowers(header: String, user: String): Single<List<User>>
    fun loadUserFollowing(header: String, user: String): Single<List<User>>
}
