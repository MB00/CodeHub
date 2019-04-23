package mb00.android.codehub.ui.search.interactor

import io.reactivex.Single
import mb00.android.codehub.api.manager.ApiCallManager
import mb00.android.codehub.api.model.IssueResult
import mb00.android.codehub.api.model.RepoResult
import mb00.android.codehub.api.model.UserResult
import mb00.android.codehub.ui.search.view.SearchActivity


class SearchInteractorImpl(val activity: SearchActivity, val apiCallManager: ApiCallManager): SearchInteractor {

    override fun search() {
        activity.search()
    }

    override fun searchIssues(header: String, user: String): Single<IssueResult> {
        return apiCallManager.loadIssueSearchResults(header, user)
    }

    override fun searchRepos(header: String, user: String): Single<RepoResult> {
        return apiCallManager.loadRepoSearchResults(header, user)
    }

    override fun searchUsers(header: String, user: String): Single<UserResult> {
        return apiCallManager.loadUserSearchResults(header, user)
    }

}