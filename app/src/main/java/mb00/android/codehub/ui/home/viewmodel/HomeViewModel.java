package mb00.android.codehub.ui.home.viewmodel;

import mb00.android.codehub.ui.base.viewmodel.BaseViewModel;
import mb00.android.codehub.ui.home.interactor.HomeInteractor;
import mb00.android.codehub.ui.home.router.HomeRouter;


public class HomeViewModel extends BaseViewModel {

    private final HomeRouter router;
    private final HomeInteractor interactor;

    public HomeViewModel(final HomeRouter router, final HomeInteractor interactor) {
        this.router = router;
        this.interactor = interactor;
    }

    public void startSearchActivity() {
        router.loadSearchActivity();
    }

    public void openNavigationDrawer() {
        interactor.openNavigationDrawer();
    }

}
