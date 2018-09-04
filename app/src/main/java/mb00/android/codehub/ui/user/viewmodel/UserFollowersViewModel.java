package mb00.android.codehub.ui.user.viewmodel;

import mb00.android.codehub.ui.user.interactor.UserInteractor;
import mb00.android.codehub.ui.user.router.UserRouter;


public class UserFollowersViewModel extends UserViewModel {

    public UserFollowersViewModel(UserInteractor interactor, UserRouter router) {
        super(interactor, router);
    }

}
