package mb00.android.codehub.ui.gist.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.Comment
import mb00.android.codehub.api.model.Gist
import mb00.android.codehub.ui.base.viewmodel.BaseViewModel
import mb00.android.codehub.ui.gist.interactor.GistInteractor
import mb00.android.codehub.ui.gist.router.GistRouter


class GistViewModel(private val interactor: GistInteractor, private val router: GistRouter) : BaseViewModel() {

    fun goBack() {
        router.finishGistActivity()
    }

    fun loadGistFiles(header: String, gist: String): Single<Gist> {
        return interactor.loadGistFiles(header, gist)
    }

    fun loadGistComments(header: String, gist: String): Single<List<Comment>> {
        return interactor.loadGistComments(header, gist)
    }

}
