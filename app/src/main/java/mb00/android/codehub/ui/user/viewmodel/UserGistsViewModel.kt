package mb00.android.codehub.ui.user.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.Gist
import mb00.android.codehub.ui.user.interactor.UserInteractor
import mb00.android.codehub.ui.user.router.UserRouter


class UserGistsViewModel(private val interactor: UserInteractor, private val router: UserRouter)
    : UserViewModel(interactor, router) {

    fun loadUserGists(header: String, user: String): Single<List<Gist>> {
        return interactor.loadUserGists(header, user)
    }

}
