package mb00.android.codehub.ui.search.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.Repo
import mb00.android.codehub.ui.search.interactor.SearchInteractor
import mb00.android.codehub.ui.search.router.SearchRouter


class SearchReposViewModel(private val interactor: SearchInteractor, private val router: SearchRouter)
    : SearchViewModel(interactor, router) {

    /*fun searchRepos(header: String, user: String): Single<List<Repo>> {
        interactor.search()
    }*/

}
