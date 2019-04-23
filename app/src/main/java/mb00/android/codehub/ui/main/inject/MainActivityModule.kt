package mb00.android.codehub.ui.main.inject

import android.content.Context

import dagger.Module
import dagger.Provides
import mb00.android.codehub.logic.preferences.PreferenceManager
import mb00.android.codehub.logic.preferences.PreferenceManagerImpl
import mb00.android.codehub.ui.main.router.MainRouter
import mb00.android.codehub.ui.main.router.MainRouterImpl
import mb00.android.codehub.ui.main.view.MainActivity
import mb00.android.codehub.ui.main.viewmodel.MainViewModel


@Module
class MainActivityModule {

    @Provides
    internal fun provideMainRouter(activity: MainActivity): MainRouter {
        return MainRouterImpl(activity)
    }

    @Provides
    internal fun providePreferenceManager(context: Context): PreferenceManager {
        return PreferenceManagerImpl(context)
    }

    @Provides
    internal fun provideMainViewModel(): MainViewModel {
        return MainViewModel()
    }

}
