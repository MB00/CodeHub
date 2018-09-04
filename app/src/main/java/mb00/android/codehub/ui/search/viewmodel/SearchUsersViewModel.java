package mb00.android.codehub.ui.search.viewmodel;

import mb00.android.codehub.ui.search.interactor.SearchInteractor;
import mb00.android.codehub.ui.search.router.SearchRouter;


public class SearchUsersViewModel extends SearchViewModel {

    public SearchUsersViewModel(SearchInteractor interactor, SearchRouter router) {
        super(interactor, router);
    }

}
