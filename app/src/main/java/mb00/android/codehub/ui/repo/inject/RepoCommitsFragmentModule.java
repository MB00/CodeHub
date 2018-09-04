package mb00.android.codehub.ui.repo.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.repo.interactor.RepoInteractor;
import mb00.android.codehub.ui.repo.router.RepoRouter;
import mb00.android.codehub.ui.repo.viewmodel.RepoCommitsViewModel;


@Module
public class RepoCommitsFragmentModule {

    @Provides
    RepoCommitsViewModel provideRepoCommitsViewModel(RepoInteractor interactor, RepoRouter router) {
        return new RepoCommitsViewModel(interactor, router);
    }

}
