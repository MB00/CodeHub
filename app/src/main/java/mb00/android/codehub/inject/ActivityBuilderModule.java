package mb00.android.codehub.inject;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import mb00.android.codehub.ui.about.inject.AboutActivityComponent;
import mb00.android.codehub.ui.about.view.AboutActivity;
import mb00.android.codehub.ui.gist.inject.GistActivityComponent;
import mb00.android.codehub.ui.gist.view.GistActivity;
import mb00.android.codehub.ui.home.inject.HomeActivityComponent;
import mb00.android.codehub.ui.home.view.HomeActivity;
import mb00.android.codehub.ui.login.inject.LoginActivityComponent;
import mb00.android.codehub.ui.login.view.LoginActivity;
import mb00.android.codehub.ui.main.inject.MainActivityComponent;
import mb00.android.codehub.ui.main.view.MainActivity;
import mb00.android.codehub.ui.repo.inject.RepoActivityComponent;
import mb00.android.codehub.ui.repo.view.RepoActivity;
import mb00.android.codehub.ui.search.inject.SearchActivityComponent;
import mb00.android.codehub.ui.search.view.SearchActivity;
import mb00.android.codehub.ui.settings.inject.SettingsActivityComponent;
import mb00.android.codehub.ui.settings.view.SettingsActivity;
import mb00.android.codehub.ui.user.inject.UserActivityComponent;
import mb00.android.codehub.ui.user.view.UserActivity;


@Module
public abstract class ActivityBuilderModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivity(MainActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindLoginActivity(LoginActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindHomeActivity(HomeActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(SearchActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindSearchActivity(SearchActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(UserActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindUserActivity(UserActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(RepoActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindRepoActivity(RepoActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(GistActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindGistActivity(GistActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(SettingsActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindSettingsActivity(SettingsActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(AboutActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindAboutActivity(AboutActivityComponent.Builder builder);

}
