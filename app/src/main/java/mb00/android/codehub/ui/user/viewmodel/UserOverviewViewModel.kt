package mb00.android.codehub.ui.user.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.User
import mb00.android.codehub.ui.user.interactor.UserInteractor
import mb00.android.codehub.ui.user.router.UserRouter


class UserOverviewViewModel(private val interactor: UserInteractor, private val router: UserRouter)
    : UserViewModel(interactor, router) {

    fun loadUserOverview(header: String, user: String): Single<User> {
        return interactor.loadUserOverview(header, user)
    }

}
