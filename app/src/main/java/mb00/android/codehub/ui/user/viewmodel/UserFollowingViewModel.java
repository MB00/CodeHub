package mb00.android.codehub.ui.user.viewmodel;

import mb00.android.codehub.ui.user.interactor.UserInteractor;
import mb00.android.codehub.ui.user.router.UserRouter;


public class UserFollowingViewModel extends UserViewModel {

    public UserFollowingViewModel(UserInteractor interactor, UserRouter router) {
        super(interactor, router);
    }

}
