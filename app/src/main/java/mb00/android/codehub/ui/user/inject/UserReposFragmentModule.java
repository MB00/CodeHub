package mb00.android.codehub.ui.user.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.user.interactor.UserInteractor;
import mb00.android.codehub.ui.user.router.UserRouter;
import mb00.android.codehub.ui.user.viewmodel.UserReposViewModel;


@Module
public class UserReposFragmentModule {

    @Provides
    UserReposViewModel provideUserReposViewModel(UserInteractor interactor, UserRouter router) {
        return new UserReposViewModel(interactor, router);
    }

}
