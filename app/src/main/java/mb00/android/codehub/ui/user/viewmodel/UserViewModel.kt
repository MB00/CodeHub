package mb00.android.codehub.ui.user.viewmodel

import mb00.android.codehub.ui.base.viewmodel.BaseViewModel
import mb00.android.codehub.ui.user.interactor.UserInteractor
import mb00.android.codehub.ui.user.router.UserRouter


open class UserViewModel(private val interactor: UserInteractor, private val router: UserRouter)
    : BaseViewModel() {

    fun goBack() {
        router.finishUserActivity()
    }

}
