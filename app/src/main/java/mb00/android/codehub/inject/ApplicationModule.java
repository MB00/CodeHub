package mb00.android.codehub.inject;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.api.manager.ApiCallManager;
import mb00.android.codehub.ui.about.inject.AboutActivityComponent;
import mb00.android.codehub.ui.gist.inject.GistActivityComponent;
import mb00.android.codehub.ui.home.inject.HomeActivityComponent;
import mb00.android.codehub.ui.login.inject.LoginActivityComponent;
import mb00.android.codehub.ui.main.inject.MainActivityComponent;
import mb00.android.codehub.ui.repo.inject.RepoActivityComponent;
import mb00.android.codehub.ui.search.inject.SearchActivityComponent;
import mb00.android.codehub.ui.settings.inject.SettingsActivityComponent;
import mb00.android.codehub.ui.user.inject.UserActivityComponent;


@Module(subcomponents = {
        MainActivityComponent.class,
        LoginActivityComponent.class,
        HomeActivityComponent.class,
        UserActivityComponent.class,
        RepoActivityComponent.class,
        GistActivityComponent.class,
        SearchActivityComponent.class,
        SettingsActivityComponent.class,
        AboutActivityComponent.class
})
public class ApplicationModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    ApiCallManager provideApiCallManager(Context context) {
        return new ApiCallManager(context);
    }

}
