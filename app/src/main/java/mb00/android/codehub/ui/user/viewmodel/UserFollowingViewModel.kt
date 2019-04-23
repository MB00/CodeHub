package mb00.android.codehub.ui.user.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.User
import mb00.android.codehub.ui.user.interactor.UserInteractor
import mb00.android.codehub.ui.user.router.UserRouter


class UserFollowingViewModel(private val interactor: UserInteractor, private val router: UserRouter)
    : UserViewModel(interactor, router) {

    fun loadUserFollowing(header: String, user: String): Single<List<User>> {
        return interactor.loadUserFollowing(header, user)
    }

}
