package mb00.android.codehub.ui.repo.viewmodel;

import mb00.android.codehub.ui.repo.interactor.RepoInteractor;
import mb00.android.codehub.ui.repo.router.RepoRouter;


public class RepoCommitsViewModel extends RepoViewModel {

    public RepoCommitsViewModel(RepoInteractor interactor, RepoRouter router) {
        super(interactor, router);
    }

}
