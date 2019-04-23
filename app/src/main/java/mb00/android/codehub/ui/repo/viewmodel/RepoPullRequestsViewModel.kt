package mb00.android.codehub.ui.repo.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.PullRequest
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter


class RepoPullRequestsViewModel(private val interactor: RepoInteractor, private val router: RepoRouter)
    : RepoViewModel(interactor, router) {

    fun loadRepoPullRequests(header: String, user: String, repo: String): Single<List<PullRequest>> {
        return interactor.loadRepoPullRequests(header, user, repo)
    }

}
