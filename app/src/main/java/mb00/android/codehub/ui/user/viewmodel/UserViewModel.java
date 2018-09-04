package mb00.android.codehub.ui.user.viewmodel;

import mb00.android.codehub.ui.base.viewmodel.BaseViewModel;
import mb00.android.codehub.ui.user.interactor.UserInteractor;
import mb00.android.codehub.ui.user.router.UserRouter;


public class UserViewModel extends BaseViewModel {

    private final UserInteractor interactor;
    private final UserRouter router;

    public UserViewModel(UserInteractor interactor, UserRouter router) {
        this.interactor = interactor;
        this.router = router;
    }

    public void goBack() {
        router.finishUserActivity();
    }

}
