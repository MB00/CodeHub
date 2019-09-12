package mb00.android.codehub.ui.repo.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mb00.android.codehub.ui.repo.view.*

@Module
abstract class RepoActivityFragmentsModule {

    @ContributesAndroidInjector(modules = [RepoCodeFragmentModule::class])
    internal abstract fun bindRepoCodeFragment(): RepoCodeFragment

    @ContributesAndroidInjector(modules = [RepoCommitsFragmentModule::class])
    internal abstract fun bindRepoCommitsFragment(): RepoCommitsFragment

    @ContributesAndroidInjector(modules = [RepoContributorsFragmentModule::class])
    internal abstract fun bindRepoContributorsFragment(): RepoContributorsFragment

    @ContributesAndroidInjector(modules = [RepoIssuesFragmentModule::class])
    internal abstract fun bindRepoIssuesFragment(): RepoIssuesFragment

    @ContributesAndroidInjector(modules = [RepoLicenseFragmentModule::class])
    internal abstract fun bindRepoLicenseFragment(): RepoLicenseFragment

    @ContributesAndroidInjector(modules = [RepoPullRequestsFragmentModule::class])
    internal abstract fun bindRepoPullRequestsFragment(): RepoPullRequestsFragment

    @ContributesAndroidInjector(modules = [RepoPulseFragmentModule::class])
    internal abstract fun bindRepoPulseFragment(): RepoPulseFragment

    @ContributesAndroidInjector(modules = [RepoReadmeFragmentModule::class])
    internal abstract fun bindRepoReadmeFragment(): RepoReadmeFragment

    @ContributesAndroidInjector(modules = [RepoReleasesFragmentModule::class])
    internal abstract fun bindRepoReleasesFragment(): RepoReleasesFragment

}