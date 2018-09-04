package mb00.android.codehub.ui.search.interactor

import mb00.android.codehub.ui.search.view.SearchActivity


class SearchInteractorImpl(val activity: SearchActivity): SearchInteractor {

    override fun search() {
        activity.search()
    }

}