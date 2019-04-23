package mb00.android.codehub.ui.user.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.user.interactor.UserInteractor
import mb00.android.codehub.ui.user.router.UserRouter
import mb00.android.codehub.ui.user.viewmodel.UserStarredViewModel


@Module
class UserStarredFragmentModule {

    @Provides
    internal fun provideUserStarredViewModel(interactor: UserInteractor, router: UserRouter): UserStarredViewModel {
        return UserStarredViewModel(interactor, router)
    }

}
