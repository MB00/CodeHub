package mb00.android.codehub.ui.user.viewmodel;

import mb00.android.codehub.ui.user.interactor.UserInteractor;
import mb00.android.codehub.ui.user.router.UserRouter;


public class UserPulseViewModel extends UserViewModel {

    private final UserInteractor interactor;
    private final UserRouter router;

    public UserPulseViewModel(UserInteractor interactor, UserRouter router) {
        super(interactor, router);
        this.interactor = interactor;
        this.router = router;
    }

    /*public void getUserPulse(String header, String user) {
        apiCallManager.loadUserPulse(header, user);
    }*/

}
