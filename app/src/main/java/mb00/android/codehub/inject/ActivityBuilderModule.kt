package mb00.android.codehub.inject

import android.app.Activity

import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import mb00.android.codehub.ui.about.inject.AboutActivityComponent
import mb00.android.codehub.ui.about.view.AboutActivity
import mb00.android.codehub.ui.gist.inject.GistActivityComponent
import mb00.android.codehub.ui.gist.view.GistActivity
import mb00.android.codehub.ui.home.inject.HomeActivityComponent
import mb00.android.codehub.ui.home.view.HomeActivity
import mb00.android.codehub.ui.login.inject.LoginActivityComponent
import mb00.android.codehub.ui.login.view.LoginActivity
import mb00.android.codehub.ui.main.inject.MainActivityComponent
import mb00.android.codehub.ui.main.view.MainActivity
import mb00.android.codehub.ui.repo.inject.RepoActivityComponent
import mb00.android.codehub.ui.repo.inject.RepoFileActivityComponent
import mb00.android.codehub.ui.repo.view.RepoActivity
import mb00.android.codehub.ui.repo.view.RepoFileActivity
import mb00.android.codehub.ui.search.inject.SearchActivityComponent
import mb00.android.codehub.ui.search.view.SearchActivity
import mb00.android.codehub.ui.settings.inject.SettingsActivityComponent
import mb00.android.codehub.ui.settings.view.SettingsActivity
import mb00.android.codehub.ui.user.inject.UserActivityComponent
import mb00.android.codehub.ui.user.view.UserActivity


@Module
abstract class ActivityBuilderModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindMainActivity(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity::class)
    internal abstract fun bindLoginActivity(builder: LoginActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity::class)
    internal abstract fun bindHomeActivity(builder: HomeActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(SearchActivity::class)
    internal abstract fun bindSearchActivity(builder: SearchActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(UserActivity::class)
    internal abstract fun bindUserActivity(builder: UserActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(RepoActivity::class)
    internal abstract fun bindRepoActivity(builder: RepoActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(RepoFileActivity::class)
    internal abstract fun bindRepoFileActivity(builder: RepoFileActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(GistActivity::class)
    internal abstract fun bindGistActivity(builder: GistActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(SettingsActivity::class)
    internal abstract fun bindSettingsActivity(builder: SettingsActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(AboutActivity::class)
    internal abstract fun bindAboutActivity(builder: AboutActivityComponent.Builder): AndroidInjector.Factory<out Activity>

}
