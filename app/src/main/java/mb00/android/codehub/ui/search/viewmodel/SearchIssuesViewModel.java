package mb00.android.codehub.ui.search.viewmodel;

import mb00.android.codehub.ui.search.interactor.SearchInteractor;
import mb00.android.codehub.ui.search.router.SearchRouter;


public class SearchIssuesViewModel extends SearchViewModel {

    public SearchIssuesViewModel(SearchInteractor interactor, SearchRouter router) {
        super(interactor, router);
    }

}
