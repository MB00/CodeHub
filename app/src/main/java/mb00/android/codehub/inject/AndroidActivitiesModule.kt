package mb00.android.codehub.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mb00.android.codehub.ui.about.inject.AboutActivityModule
import mb00.android.codehub.ui.about.view.AboutActivity
import mb00.android.codehub.ui.gist.inject.GistActivityModule
import mb00.android.codehub.ui.gist.view.GistActivity
import mb00.android.codehub.ui.home.inject.HomeActivityModule
import mb00.android.codehub.ui.home.view.HomeActivity
import mb00.android.codehub.ui.login.inject.LoginActivityModule
import mb00.android.codehub.ui.login.view.LoginActivity
import mb00.android.codehub.ui.main.inject.MainActivityModule
import mb00.android.codehub.ui.main.view.MainActivity
import mb00.android.codehub.ui.repo.inject.RepoActivityModule
import mb00.android.codehub.ui.repo.inject.RepoFileActivityModule
import mb00.android.codehub.ui.repo.view.RepoActivity
import mb00.android.codehub.ui.repo.view.RepoFileActivity
import mb00.android.codehub.ui.search.inject.SearchActivityModule
import mb00.android.codehub.ui.search.view.SearchActivity
import mb00.android.codehub.ui.settings.inject.SettingsActivityModule
import mb00.android.codehub.ui.settings.view.SettingsActivity
import mb00.android.codehub.ui.user.inject.UserActivityModule
import mb00.android.codehub.ui.user.view.UserActivity


@Module
abstract class AndroidActivitiesModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @ActivityLifecycle
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    @ActivityLifecycle
    internal abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    @ActivityLifecycle
    internal abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [UserActivityModule::class])
    @ActivityLifecycle
    internal abstract fun bindUserActivity(): UserActivity

    @ContributesAndroidInjector(modules = [RepoActivityModule::class])
    @ActivityLifecycle
    internal abstract fun bindRepoActivity(): RepoActivity

    @ContributesAndroidInjector(modules = [RepoFileActivityModule::class])
    @ActivityLifecycle
    internal abstract fun bindRepoFileActivity(): RepoFileActivity

    @ContributesAndroidInjector(modules = [GistActivityModule::class])
    @ActivityLifecycle
    internal abstract fun bindGistActivity(): GistActivity

    @ContributesAndroidInjector(modules = [SearchActivityModule::class])
    @ActivityLifecycle
    internal abstract fun bindSearchActivity(): SearchActivity

    @ContributesAndroidInjector(modules = [SettingsActivityModule::class])
    @ActivityLifecycle
    internal abstract fun bindSettingsActivity(): SettingsActivity

    @ContributesAndroidInjector(modules = [AboutActivityModule::class])
    @ActivityLifecycle
    internal abstract fun bindAboutActivity(): AboutActivity

}
