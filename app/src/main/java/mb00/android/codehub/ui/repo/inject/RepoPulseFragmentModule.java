package mb00.android.codehub.ui.repo.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.repo.interactor.RepoInteractor;
import mb00.android.codehub.ui.repo.router.RepoRouter;
import mb00.android.codehub.ui.repo.viewmodel.RepoPulseViewModel;


@Module
public class RepoPulseFragmentModule {

    @Provides
    RepoPulseViewModel provideRepoPulseViewModel(RepoInteractor interactor, RepoRouter router) {
        return new RepoPulseViewModel(interactor, router);
    }

}
