package mb00.android.codehub.ui.main.inject;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.logic.preferences.PreferenceManager;
import mb00.android.codehub.logic.preferences.PreferenceManagerImpl;
import mb00.android.codehub.ui.main.router.MainRouter;
import mb00.android.codehub.ui.main.router.MainRouterImpl;
import mb00.android.codehub.ui.main.view.MainActivity;
import mb00.android.codehub.ui.main.viewmodel.MainViewModel;


@Module
public class MainActivityModule {

    @Provides
    MainRouter provideMainRouter(final MainActivity activity) {
        return new MainRouterImpl(activity);
    }

    @Provides
    PreferenceManager providePreferenceManager(final Context context) {
        return new PreferenceManagerImpl(context);
    }

    @Provides
    MainViewModel provideMainViewModel() {
        return new MainViewModel();
    }

}
