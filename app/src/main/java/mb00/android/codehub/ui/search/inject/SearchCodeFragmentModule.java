package mb00.android.codehub.ui.search.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.search.interactor.SearchInteractor;
import mb00.android.codehub.ui.search.router.SearchRouter;
import mb00.android.codehub.ui.search.viewmodel.SearchCodeViewModel;


@Module
public class SearchCodeFragmentModule {

    @Provides
    SearchCodeViewModel provideSearchCodeViewModel(SearchInteractor interactor, SearchRouter router) {
        return new SearchCodeViewModel(interactor, router);
    }

}
