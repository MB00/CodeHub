package mb00.android.codehub.ui.home.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.api.manager.ApiCallManager
import mb00.android.codehub.ui.home.interactor.HomeInteractor
import mb00.android.codehub.ui.home.interactor.HomeInteractorImpl
import mb00.android.codehub.ui.home.router.HomeRouter
import mb00.android.codehub.ui.home.router.HomeRouterImpl
import mb00.android.codehub.ui.home.view.HomeActivity
import mb00.android.codehub.ui.home.viewmodel.HomeViewModel


@Module(subcomponents = [
    HomePulseFragmentComponent::class,
    HomeReposFragmentComponent::class,
    HomeFollowersFragmentComponent::class,
    HomeFollowingFragmentComponent::class
])
class HomeActivityModule {

    @Provides
    internal fun provideHomeRouter(activity: HomeActivity): HomeRouter {
        return HomeRouterImpl(activity)
    }

    @Provides
    internal fun provideHomeInteractor(activity: HomeActivity, apiCallManager: ApiCallManager): HomeInteractor {
        return HomeInteractorImpl(activity, apiCallManager)
    }

    @Provides
    internal fun providesHomeViewModel(router: HomeRouter, interactor: HomeInteractor): HomeViewModel {
        return HomeViewModel(router, interactor)
    }

}
