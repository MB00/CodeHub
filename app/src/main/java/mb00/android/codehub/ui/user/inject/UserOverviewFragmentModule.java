package mb00.android.codehub.ui.user.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.user.interactor.UserInteractor;
import mb00.android.codehub.ui.user.router.UserRouter;
import mb00.android.codehub.ui.user.viewmodel.UserOverviewViewModel;


@Module
public class UserOverviewFragmentModule {

    @Provides
    UserOverviewViewModel provideUserOverviewViewModel(UserInteractor interactor, UserRouter router) {
        return new UserOverviewViewModel(interactor, router);
    }

}
