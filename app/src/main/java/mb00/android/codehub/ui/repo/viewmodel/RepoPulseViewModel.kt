package mb00.android.codehub.ui.repo.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.Pulse
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter


class RepoPulseViewModel(private val interactor: RepoInteractor, private val router: RepoRouter)
    : RepoViewModel(interactor, router) {

    fun loadRepoPulse(header: String, user: String, repo: String): Single<List<Pulse>> {
        return interactor.loadRepoPulse(header, user, repo)
    }

}
