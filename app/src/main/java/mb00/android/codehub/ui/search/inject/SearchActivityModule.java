package mb00.android.codehub.ui.search.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.search.interactor.SearchInteractor;
import mb00.android.codehub.ui.search.interactor.SearchInteractorImpl;
import mb00.android.codehub.ui.search.router.SearchRouter;
import mb00.android.codehub.ui.search.router.SearchRouterImpl;
import mb00.android.codehub.ui.search.view.SearchActivity;
import mb00.android.codehub.ui.search.viewmodel.SearchViewModel;


@Module(subcomponents = {
        SearchCodeFragmentComponent.class,
        SearchIssuesFragmentComponent.class,
        SearchReposFragmentComponent.class,
        SearchUsersFragmentComponent.class
})
public class SearchActivityModule {

    @Provides
    SearchInteractor provideSearchInteractor(final SearchActivity activity) {
        return new SearchInteractorImpl(activity);
    }

    @Provides
    SearchRouter provideSearchRouter(final SearchActivity activity) {
        return new SearchRouterImpl(activity);
    }

    @Provides
    SearchViewModel provideSearchViewModel(SearchInteractor interactor, SearchRouter router) {
        return new SearchViewModel(interactor, router);
    }

}
