package mb00.android.codehub.ui.user.interactor

import io.reactivex.Single
import mb00.android.codehub.api.manager.ApiCallManager
import mb00.android.codehub.api.model.Gist
import mb00.android.codehub.api.model.Pulse
import mb00.android.codehub.api.model.Repo
import mb00.android.codehub.api.model.User


class UserInteractorImpl(private val apiCallManager: ApiCallManager) : UserInteractor {

    override fun loadUserOverview(header: String, user: String): Single<User> {
        return apiCallManager.loadUserOverview(header, user)
    }

    override fun loadUserPulse(header: String, user: String): Single<List<Pulse>> {
        return apiCallManager.loadUserPulse(header, user)
    }

    override fun loadUserRepos(header: String, user: String): Single<List<Repo>> {
        return apiCallManager.loadUserRepos(header, user)
    }

    override fun loadUserGists(header: String, user: String): Single<List<Gist>> {
        return apiCallManager.loadUserGists(header, user)
    }

    override fun loadUserStarred(header: String, user: String): Single<List<Repo>> {
        return apiCallManager.loadUserStarred(header, user)
    }

    override fun loadUserFollowers(header: String, user: String): Single<List<User>> {
        return apiCallManager.loadUserFollowers(header, user)
    }

    override fun loadUserFollowing(header: String, user: String): Single<List<User>> {
        return apiCallManager.loadUserFollowing(header, user)
    }

}
