package mb00.android.codehub.ui.search.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.search.view.SearchIssuesFragment;


@Subcomponent(modules = SearchIssuesFragmentModule.class)
public interface SearchIssuesFragmentComponent extends AndroidInjector<SearchIssuesFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SearchIssuesFragment> {}

}
