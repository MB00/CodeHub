package mb00.android.codehub.ui.search.viewmodel;

import mb00.android.codehub.ui.base.viewmodel.BaseViewModel;
import mb00.android.codehub.ui.search.interactor.SearchInteractor;
import mb00.android.codehub.ui.search.router.SearchRouter;


public class SearchViewModel extends BaseViewModel {

    private final SearchInteractor interactor;
    private final SearchRouter router;

    public SearchViewModel(SearchInteractor interactor, SearchRouter router) {
        this.interactor = interactor;
        this.router = router;
    }

    public void search() {
        interactor.search();
    }

    public void goBack() {
        router.loadPrevious();
    }

}
