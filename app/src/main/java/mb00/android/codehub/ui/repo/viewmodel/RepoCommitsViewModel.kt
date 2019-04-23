package mb00.android.codehub.ui.repo.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.Commit
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter


class RepoCommitsViewModel(private val interactor: RepoInteractor, private val router: RepoRouter)
    : RepoViewModel(interactor, router) {

    fun loadRepoCommits(header: String, user: String, repo: String): Single<List<Commit>> {
        return interactor.loadRepoCommits(header, user, repo)
    }

}
