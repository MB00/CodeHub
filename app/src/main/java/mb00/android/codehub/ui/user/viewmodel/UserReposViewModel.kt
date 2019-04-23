package mb00.android.codehub.ui.user.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.Repo
import mb00.android.codehub.ui.user.interactor.UserInteractor
import mb00.android.codehub.ui.user.router.UserRouter


class UserReposViewModel(private val interactor: UserInteractor, private val router: UserRouter)
    : UserViewModel(interactor, router) {

    fun loadUserRepos(header: String, user: String): Single<List<Repo>> {
        return interactor.loadUserRepos(header, user)
    }

}
