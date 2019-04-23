package mb00.android.codehub.ui.repo.inject

import android.support.v4.app.Fragment

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import mb00.android.codehub.ui.repo.view.RepoCodeFragment
import mb00.android.codehub.ui.repo.view.RepoCommitsFragment
import mb00.android.codehub.ui.repo.view.RepoContributorsFragment
import mb00.android.codehub.ui.repo.view.RepoIssuesFragment
import mb00.android.codehub.ui.repo.view.RepoLicenseFragment
import mb00.android.codehub.ui.repo.view.RepoPullRequestsFragment
import mb00.android.codehub.ui.repo.view.RepoPulseFragment
import mb00.android.codehub.ui.repo.view.RepoReadmeFragment
import mb00.android.codehub.ui.repo.view.RepoReleasesFragment


@Module
abstract class RepoFragmentProvider {

    @Binds
    @IntoMap
    @FragmentKey(RepoCodeFragment::class)
    internal abstract fun bindRepoCodeFragment(builder: RepoCodeFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(RepoCommitsFragment::class)
    internal abstract fun bindRepoCommitsFragment(builder: RepoCommitsFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(RepoContributorsFragment::class)
    internal abstract fun bindRepoContributorsFragment(builder: RepoContributorsFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(RepoIssuesFragment::class)
    internal abstract fun bindRepoIssuesFragment(builder: RepoIssuesFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(RepoLicenseFragment::class)
    internal abstract fun bindRepoLicenseFragment(builder: RepoLicenseFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(RepoPullRequestsFragment::class)
    internal abstract fun bindRepoPullRequestsFragment(builder: RepoPullRequestsFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(RepoPulseFragment::class)
    internal abstract fun bindRepoPulseFragment(builder: RepoPulseFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(RepoReadmeFragment::class)
    internal abstract fun bindRepoReadmeFragment(builder: RepoReadmeFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(RepoReleasesFragment::class)
    internal abstract fun bindRepoReleasesFragment(builder: RepoReleasesFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

}
