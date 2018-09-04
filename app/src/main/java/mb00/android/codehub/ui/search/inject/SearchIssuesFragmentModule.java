package mb00.android.codehub.ui.search.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.search.interactor.SearchInteractor;
import mb00.android.codehub.ui.search.router.SearchRouter;
import mb00.android.codehub.ui.search.viewmodel.SearchIssuesViewModel;


@Module
public class SearchIssuesFragmentModule {

    @Provides
    SearchIssuesViewModel provideSearchIssuesViewModel(SearchInteractor interactor, SearchRouter router) {
        return new SearchIssuesViewModel(interactor, router);
    }

}
