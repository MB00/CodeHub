package mb00.android.codehub.ui.user.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.api.manager.ApiCallManager
import mb00.android.codehub.ui.user.interactor.UserInteractor
import mb00.android.codehub.ui.user.interactor.UserInteractorImpl
import mb00.android.codehub.ui.user.router.UserRouter
import mb00.android.codehub.ui.user.router.UserRouterImpl
import mb00.android.codehub.ui.user.view.UserActivity
import mb00.android.codehub.ui.user.viewmodel.UserViewModel

@Module(includes = [UserActivityFragmentsModule::class])
class UserActivityModule {

    @Provides
    internal fun provideUserInteractor(apiCallManager: ApiCallManager): UserInteractor {
        return UserInteractorImpl(apiCallManager)
    }

    @Provides
    internal fun provideUserRouter(activity: UserActivity): UserRouter {
        return UserRouterImpl(activity)
    }

    @Provides
    internal fun provideUserViewModel(interactor: UserInteractor, router: UserRouter): UserViewModel {
        return UserViewModel(interactor, router)
    }

}
