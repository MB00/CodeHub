package mb00.android.codehub.ui.user.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.User
import mb00.android.codehub.ui.user.interactor.UserInteractor
import mb00.android.codehub.ui.user.router.UserRouter


class UserFollowersViewModel(private val interactor: UserInteractor, private val router: UserRouter)
    : UserViewModel(interactor, router) {

    fun loadUserFollowers(header: String, user: String): Single<List<User>> {
        return interactor.loadUserFollowers(header, user)
    }

}
