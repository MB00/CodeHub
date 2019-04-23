package mb00.android.codehub.ui.repo.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.Issue
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter


class RepoIssuesViewModel(private val interactor: RepoInteractor, private val router: RepoRouter)
    : RepoViewModel(interactor, router) {

    fun loadRepoIssues(header: String, user: String, repo: String): Single<List<Issue>> {
        return interactor.loadRepoIssues(header, user, repo)
    }

}
