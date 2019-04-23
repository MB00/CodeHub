package mb00.android.codehub.ui.search.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.IssueResult
import mb00.android.codehub.api.model.RepoResult
import mb00.android.codehub.api.model.UserResult
import mb00.android.codehub.ui.base.viewmodel.BaseViewModel
import mb00.android.codehub.ui.search.interactor.SearchInteractor
import mb00.android.codehub.ui.search.router.SearchRouter


open class SearchViewModel(private val interactor: SearchInteractor, private val router: SearchRouter) : BaseViewModel() {

    fun search() {
        interactor.search()
    }

    fun goBack() {
        router.loadPrevious()
    }

    fun searchIssues(header: String, user: String): Single<IssueResult> {
        return interactor.searchIssues(header, user)
    }

    fun searchRepos(header: String, user: String): Single<RepoResult> {
        return interactor.searchRepos(header, user)
    }

    fun searchUsers(header: String, user: String): Single<UserResult> {
        return interactor.searchUsers(header, user)
    }

}
