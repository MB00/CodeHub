package mb00.android.codehub.ui.repo.viewmodel;

import mb00.android.codehub.ui.base.viewmodel.BaseViewModel;
import mb00.android.codehub.ui.repo.interactor.RepoInteractor;
import mb00.android.codehub.ui.repo.router.RepoRouter;


public class RepoViewModel extends BaseViewModel {

    private final RepoInteractor interactor;
    private final RepoRouter router;

    public RepoViewModel(RepoInteractor interactor, RepoRouter router) {
        this.interactor = interactor;
        this.router = router;
    }

    public void goBack() {
        router.finishRepoActivity();
    }

}
