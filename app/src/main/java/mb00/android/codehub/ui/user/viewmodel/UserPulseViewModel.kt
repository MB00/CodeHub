package mb00.android.codehub.ui.user.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.Pulse
import mb00.android.codehub.ui.user.interactor.UserInteractor
import mb00.android.codehub.ui.user.router.UserRouter


class UserPulseViewModel(private val interactor: UserInteractor, private val router: UserRouter) : UserViewModel(interactor, router) {

    fun loadUserPulse(header: String, user: String): Single<List<Pulse>> {
        return interactor.loadUserPulse(header, user)
    }

}
