package mb00.android.codehub.ui.home.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.user.interactor.UserInteractor
import mb00.android.codehub.ui.user.router.UserRouter
import mb00.android.codehub.ui.user.viewmodel.UserReposViewModel


@Module
class HomeReposFragmentModule {

    @Provides
    internal fun provideUserReposViewModel(interactor: UserInteractor, router: UserRouter): UserReposViewModel {
        return UserReposViewModel(interactor, router)
    }

}
