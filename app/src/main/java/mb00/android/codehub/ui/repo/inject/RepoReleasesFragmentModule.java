package mb00.android.codehub.ui.repo.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.repo.interactor.RepoInteractor;
import mb00.android.codehub.ui.repo.router.RepoRouter;
import mb00.android.codehub.ui.repo.viewmodel.RepoReleasesViewModel;


@Module
public class RepoReleasesFragmentModule {

    @Provides
    RepoReleasesViewModel provideRepoReleasesViewModel(RepoInteractor interactor, RepoRouter router) {
        return new RepoReleasesViewModel(interactor, router);
    }

}
