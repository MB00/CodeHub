package mb00.android.codehub.ui.repo.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.repo.interactor.RepoInteractor;
import mb00.android.codehub.ui.repo.interactor.RepoInteractorImpl;
import mb00.android.codehub.ui.repo.router.RepoRouter;
import mb00.android.codehub.ui.repo.router.RepoRouterImpl;
import mb00.android.codehub.ui.repo.view.RepoActivity;
import mb00.android.codehub.ui.repo.viewmodel.RepoViewModel;


@Module(subcomponents = {
        RepoCodeFragmentComponent.class,
        RepoCommitsFragmentComponent.class,
        RepoContributorsFragmentComponent.class,
        RepoIssuesFragmentComponent.class,
        RepoLicenseFragmentComponent.class,
        RepoPullRequestsFragmentComponent.class,
        RepoPulseFragmentComponent.class,
        RepoReadmeFragmentComponent.class,
        RepoReleasesFragmentComponent.class
})
public class RepoActivityModule {

    @Provides
    RepoInteractor provideRepoInteractor() {
        return new RepoInteractorImpl();
    }

    @Provides
    RepoRouter provideRepoRouter(RepoActivity activity) {
        return new RepoRouterImpl(activity);
    }

    @Provides
    RepoViewModel provideRepoViewModel(RepoInteractor interactor, RepoRouter router) {
        return new RepoViewModel(interactor, router);
    }

}
