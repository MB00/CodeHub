package mb00.android.codehub.ui.search.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.search.view.SearchCodeFragment;


@Subcomponent(modules = SearchCodeFragmentModule.class)
public interface SearchCodeFragmentComponent extends AndroidInjector<SearchCodeFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SearchCodeFragment> {}

}
