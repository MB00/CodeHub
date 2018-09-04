package mb00.android.codehub.ui.repo.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.repo.interactor.RepoInteractor;
import mb00.android.codehub.ui.repo.router.RepoRouter;
import mb00.android.codehub.ui.repo.viewmodel.RepoPullRequestsViewModel;


@Module
public class RepoPullRequestsFragmentModule {

    @Provides
    RepoPullRequestsViewModel provideRepoPullRequestsViewModel(RepoInteractor interactor, RepoRouter router) {
        return new RepoPullRequestsViewModel(interactor, router);
    }

}
