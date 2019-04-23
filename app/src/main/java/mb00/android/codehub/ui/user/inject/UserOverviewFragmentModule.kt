package mb00.android.codehub.ui.user.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.user.interactor.UserInteractor
import mb00.android.codehub.ui.user.router.UserRouter
import mb00.android.codehub.ui.user.viewmodel.UserOverviewViewModel


@Module
class UserOverviewFragmentModule {

    @Provides
    internal fun provideUserOverviewViewModel(interactor: UserInteractor, router: UserRouter): UserOverviewViewModel {
        return UserOverviewViewModel(interactor, router)
    }

}
