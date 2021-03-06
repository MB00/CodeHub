package mb00.android.codehub.ui.user.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.user.interactor.UserInteractor
import mb00.android.codehub.ui.user.router.UserRouter
import mb00.android.codehub.ui.user.viewmodel.UserFollowingViewModel


@Module
class UserFollowingFragmentModule {

    @Provides
    internal fun provideUserFollowingViewModel(interactor: UserInteractor, router: UserRouter): UserFollowingViewModel {
        return UserFollowingViewModel(interactor, router)
    }

}
