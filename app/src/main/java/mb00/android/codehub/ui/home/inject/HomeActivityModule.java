package mb00.android.codehub.ui.home.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.home.interactor.HomeInteractor;
import mb00.android.codehub.ui.home.interactor.HomeInteractorImpl;
import mb00.android.codehub.ui.home.router.HomeRouter;
import mb00.android.codehub.ui.home.router.HomeRouterImpl;
import mb00.android.codehub.ui.home.view.HomeActivity;
import mb00.android.codehub.ui.home.viewmodel.HomeViewModel;

@Module(subcomponents = {
        HomePulseFragmentComponent.class,
        HomeReposFragmentComponent.class,
        HomeFollowersFragmentComponent.class,
        HomeFollowingFragmentComponent.class
})
public class HomeActivityModule {

    @Provides
    HomeRouter provideHomeRouter(final HomeActivity activity) {
        return new HomeRouterImpl(activity);
    }

    @Provides
    HomeInteractor provideHomeInteractor(final HomeActivity activity) {
        return new HomeInteractorImpl(activity);
    }

    @Provides
    HomeViewModel providesHomeViewModel(final HomeRouter router, final HomeInteractor interactor) {
        return new HomeViewModel(router, interactor);
    }

}
