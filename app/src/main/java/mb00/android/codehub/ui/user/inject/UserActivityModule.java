package mb00.android.codehub.ui.user.inject;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.user.interactor.UserInteractor;
import mb00.android.codehub.ui.user.interactor.UserInteractorImpl;
import mb00.android.codehub.ui.user.router.UserRouter;
import mb00.android.codehub.ui.user.router.UserRouterImpl;
import mb00.android.codehub.ui.user.view.UserActivity;
import mb00.android.codehub.ui.user.viewmodel.UserViewModel;

@Module(subcomponents = {
        UserFollowersFragmentComponent.class,
        UserFollowingFragmentComponent.class,
        UserGistsFragmentComponent.class,
        UserOverviewFragmentComponent.class,
        UserPulseFragmentComponent.class,
        UserReposFragmentComponent.class,
        UserStarredFragmentComponent.class
})
public class UserActivityModule {

    @Provides
    UserInteractor provideUserInteractor() {
        return new UserInteractorImpl();
    }

    @Provides
    UserRouter provideUserRouter(UserActivity activity) {
        return new UserRouterImpl(activity);
    }

    @Provides
    UserViewModel provideUserViewModel(UserInteractor interactor, UserRouter router) {
        return new UserViewModel(interactor, router);
    }

}
