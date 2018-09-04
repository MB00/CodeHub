package mb00.android.codehub.ui.repo.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.repo.interactor.RepoInteractor;
import mb00.android.codehub.ui.repo.router.RepoRouter;
import mb00.android.codehub.ui.repo.viewmodel.RepoCodeViewModel;


@Module
public class RepoCodeFragmentModule {

    @Provides
    RepoCodeViewModel provideRepoCodeViewModel(RepoInteractor interactor, RepoRouter router) {
        return new RepoCodeViewModel(interactor, router);
    }

}
