package mb00.android.codehub.ui.search.interactor

import io.reactivex.Single
import mb00.android.codehub.api.model.IssueResult
import mb00.android.codehub.api.model.RepoResult
import mb00.android.codehub.api.model.UserResult

interface SearchInteractor {
    fun search()
    fun searchIssues(header: String, user: String): Single<IssueResult>
    fun searchRepos(header: String, user: String): Single<RepoResult>
    fun searchUsers(header: String, user: String): Single<UserResult>
}