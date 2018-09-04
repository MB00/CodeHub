package mb00.android.codehub.ui.repo.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.repo.interactor.RepoInteractor;
import mb00.android.codehub.ui.repo.router.RepoRouter;
import mb00.android.codehub.ui.repo.viewmodel.RepoReadmeViewModel;


@Module
public class RepoReadmeFragmentModule {

    @Provides
    RepoReadmeViewModel provideRepoReadmeViewModel(RepoInteractor interactor, RepoRouter router) {
        return new RepoReadmeViewModel(interactor, router);
    }

}
