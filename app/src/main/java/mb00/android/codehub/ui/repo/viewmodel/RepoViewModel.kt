package mb00.android.codehub.ui.repo.viewmodel

import mb00.android.codehub.ui.base.viewmodel.BaseViewModel
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter


open class RepoViewModel(private val interactor: RepoInteractor, private val router: RepoRouter)
    : BaseViewModel() {

    fun goBack() {
        router.finishRepoActivity()
    }

}
