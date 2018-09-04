package mb00.android.codehub.ui.home.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.user.interactor.UserInteractor;
import mb00.android.codehub.ui.user.router.UserRouter;
import mb00.android.codehub.ui.user.viewmodel.UserPulseViewModel;


@Module
public class HomePulseFragmentModule {

    @Provides
    UserPulseViewModel provideUserPulseViewModel(UserInteractor interactor, UserRouter router) {
        return new UserPulseViewModel(interactor, router);
    }

}
