package mb00.android.codehub.ui.repo.viewmodel;

import mb00.android.codehub.ui.repo.interactor.RepoInteractor;
import mb00.android.codehub.ui.repo.router.RepoRouter;


public class RepoPullRequestsViewModel extends RepoViewModel {

    public RepoPullRequestsViewModel(RepoInteractor interactor, RepoRouter router) {
        super(interactor, router);
    }

}
