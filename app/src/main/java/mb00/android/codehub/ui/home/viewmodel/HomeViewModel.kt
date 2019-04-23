package mb00.android.codehub.ui.home.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.Pulse
import mb00.android.codehub.api.model.Repo
import mb00.android.codehub.api.model.User
import mb00.android.codehub.ui.base.viewmodel.BaseViewModel
import mb00.android.codehub.ui.home.interactor.HomeInteractor
import mb00.android.codehub.ui.home.router.HomeRouter


class HomeViewModel(private val router: HomeRouter, private val interactor: HomeInteractor) : BaseViewModel() {

    fun startSearchActivity() {
        router.loadSearchActivity()
    }

    fun openNavigationDrawer() {
        interactor.openNavigationDrawer()
    }

    fun loadUserFollowers(header: String, user: String): Single<List<User>> {
        return interactor.loadUserFollowers(header, user)
    }

    fun loadUserFollowing(header: String, user: String): Single<List<User>> {
        return interactor.loadUserFollowing(header, user)
    }

    fun loadUserPulse(header: String, user: String): Single<List<Pulse>> {
        return interactor.loadUserPulse(header, user)
    }

    fun loadUserRepos(header: String, user: String): Single<List<Repo>> {
        return interactor.loadUserRepos(header, user)
    }

}
