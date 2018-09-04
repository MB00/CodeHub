package mb00.android.codehub.ui.login.viewmodel;

import android.content.Context;

import mb00.android.codehub.logic.preferences.PreferenceManager;
import mb00.android.codehub.ui.base.viewmodel.BaseViewModel;
import mb00.android.codehub.ui.login.router.LoginRouter;

public class LoginViewModel extends BaseViewModel {

    final Context context;
    final LoginRouter router;
    final PreferenceManager preferenceManager;

    public LoginViewModel(Context context, LoginRouter router, PreferenceManager preferenceManager) {
        this.context = context;
        this.router = router;
        this.preferenceManager = preferenceManager;
    }

}
