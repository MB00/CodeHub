package mb00.android.codehub.ui.repo.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.Code
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter


class RepoLicenseViewModel(private val interactor: RepoInteractor, private val router: RepoRouter)
    : RepoViewModel(interactor, router) {

    fun loadRepoLicense(header: String, user: String, repo: String): Single<Code> {
        return interactor.loadRepoLicense(header, user, repo)
    }

}
