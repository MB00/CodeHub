package mb00.android.codehub.ui.home.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.user.interactor.UserInteractor
import mb00.android.codehub.ui.user.router.UserRouter
import mb00.android.codehub.ui.user.viewmodel.UserFollowersViewModel


@Module
class HomeFollowersFragmentModule {

    @Provides
    internal fun provideUserFollowersViewModel(interactor: UserInteractor, router: UserRouter): UserFollowersViewModel {
        return UserFollowersViewModel(interactor, router)
    }

}
