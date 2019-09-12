package mb00.android.codehub.ui.search.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mb00.android.codehub.ui.search.view.SearchCodeFragment
import mb00.android.codehub.ui.search.view.SearchIssuesFragment
import mb00.android.codehub.ui.search.view.SearchReposFragment
import mb00.android.codehub.ui.search.view.SearchUsersFragment


@Module
abstract class SearchActivityFragmentsModule {

    @ContributesAndroidInjector(modules = [SearchCodeFragmentModule::class])
    internal abstract fun bindSearchCodeFragment(): SearchCodeFragment

    @ContributesAndroidInjector(modules = [SearchIssuesFragmentModule::class])
    internal abstract fun bindSearchIssuesFragment(): SearchIssuesFragment

    @ContributesAndroidInjector(modules = [SearchReposFragmentModule::class])
    internal abstract fun bindSearchReposFragment(): SearchReposFragment

    @ContributesAndroidInjector(modules = [SearchUsersFragmentModule::class])
    internal abstract fun bindSearchUsersFragment(): SearchUsersFragment

}
