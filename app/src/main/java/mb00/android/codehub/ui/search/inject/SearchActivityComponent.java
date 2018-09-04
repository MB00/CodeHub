package mb00.android.codehub.ui.search.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.search.view.SearchActivity;


@Subcomponent(modules = {
        SearchActivityModule.class,
        SearchFragmentProvider.class
})
public interface SearchActivityComponent extends AndroidInjector<SearchActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SearchActivity> {}

}
