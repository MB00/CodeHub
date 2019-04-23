package mb00.android.codehub.ui.login.inject

import android.content.Context

import dagger.Module
import dagger.Provides
import mb00.android.codehub.logic.preferences.PreferenceManager
import mb00.android.codehub.logic.preferences.PreferenceManagerImpl
import mb00.android.codehub.ui.login.router.LoginRouter
import mb00.android.codehub.ui.login.router.LoginRouterImpl
import mb00.android.codehub.ui.login.view.LoginActivity
import mb00.android.codehub.ui.login.viewmodel.LoginViewModel


@Module
class LoginActivityModule {

    @Provides
    internal fun provideLoginRouter(activity: LoginActivity): LoginRouter {
        return LoginRouterImpl(activity)
    }

    @Provides
    internal fun providePreferenceManager(context: Context): PreferenceManager {
        return PreferenceManagerImpl(context)
    }

    @Provides
    internal fun provideLoginViewModel(context: Context, router: LoginRouter, preferenceManager: PreferenceManager): LoginViewModel {
        return LoginViewModel(context, router, preferenceManager)
    }

}
