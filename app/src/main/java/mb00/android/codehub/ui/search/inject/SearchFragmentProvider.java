package mb00.android.codehub.ui.search.inject;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import mb00.android.codehub.ui.search.view.SearchCodeFragment;
import mb00.android.codehub.ui.search.view.SearchIssuesFragment;
import mb00.android.codehub.ui.search.view.SearchReposFragment;
import mb00.android.codehub.ui.search.view.SearchUsersFragment;


@Module
public abstract class SearchFragmentProvider {
    
    @Binds
    @IntoMap
    @FragmentKey(SearchCodeFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindSearchCodeFragment(SearchCodeFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(SearchIssuesFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindSearchIssuesFragment(SearchIssuesFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(SearchReposFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindSearchReposFragment(SearchReposFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(SearchUsersFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindSearchUsersFragment(SearchUsersFragmentComponent.Builder builder);
    
}
