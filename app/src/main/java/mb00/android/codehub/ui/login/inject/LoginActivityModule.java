package mb00.android.codehub.ui.login.inject;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.logic.preferences.PreferenceManager;
import mb00.android.codehub.logic.preferences.PreferenceManagerImpl;
import mb00.android.codehub.ui.login.router.LoginRouter;
import mb00.android.codehub.ui.login.router.LoginRouterImpl;
import mb00.android.codehub.ui.login.view.LoginActivity;
import mb00.android.codehub.ui.login.viewmodel.LoginViewModel;


@Module
public class LoginActivityModule {

    /*@Provides
    LoginView provideLoginView(final LoginActivity loginActivity) {
        return loginActivity;
    }*/

    @Provides
    LoginRouter provideLoginRouter(final LoginActivity activity) {
        return new LoginRouterImpl(activity);
    }

    @Provides
    PreferenceManager providePreferenceManager(final Context context) {
        return new PreferenceManagerImpl(context);
    }

    @Provides
    LoginViewModel provideLoginViewModel(final Context context, final LoginRouter router, final PreferenceManager preferenceManager) {
        return new LoginViewModel(context, router, preferenceManager);
    }

}
