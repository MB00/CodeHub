package mb00.android.codehub.ui.repo.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.Contributor
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter


class RepoContributorsViewModel(private val interactor: RepoInteractor, private val router: RepoRouter)
    : RepoViewModel(interactor, router) {

    fun loadRepoContributors(header: String, user: String, repo: String): Single<List<Contributor>> {
        return interactor.loadRepoContributors(header, user, repo)
    }

}
