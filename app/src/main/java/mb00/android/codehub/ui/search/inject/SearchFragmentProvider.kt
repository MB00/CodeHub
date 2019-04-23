package mb00.android.codehub.ui.search.inject

import android.support.v4.app.Fragment

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import mb00.android.codehub.ui.search.view.SearchCodeFragment
import mb00.android.codehub.ui.search.view.SearchIssuesFragment
import mb00.android.codehub.ui.search.view.SearchReposFragment
import mb00.android.codehub.ui.search.view.SearchUsersFragment


@Module
abstract class SearchFragmentProvider {

    @Binds
    @IntoMap
    @FragmentKey(SearchCodeFragment::class)
    internal abstract fun bindSearchCodeFragment(builder: SearchCodeFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(SearchIssuesFragment::class)
    internal abstract fun bindSearchIssuesFragment(builder: SearchIssuesFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(SearchReposFragment::class)
    internal abstract fun bindSearchReposFragment(builder: SearchReposFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(SearchUsersFragment::class)
    internal abstract fun bindSearchUsersFragment(builder: SearchUsersFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

}
