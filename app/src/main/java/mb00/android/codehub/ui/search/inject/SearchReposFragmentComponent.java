package mb00.android.codehub.ui.search.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.search.view.SearchReposFragment;


@Subcomponent(modules = SearchReposFragmentModule.class)
public interface SearchReposFragmentComponent extends AndroidInjector<SearchReposFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SearchReposFragment> {}

}
