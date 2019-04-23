package mb00.android.codehub.ui.gist.interactor

import io.reactivex.Single
import mb00.android.codehub.api.manager.ApiCallManager
import mb00.android.codehub.api.model.Comment
import mb00.android.codehub.api.model.Gist

class GistInteractorImpl(private val apiCallManager: ApiCallManager) : GistInteractor {

    override fun loadGistFiles(header: String, gist: String): Single<Gist> {
        return apiCallManager.loadGistContents(header, gist)
    }

    override fun loadGistComments(header: String, gist: String): Single<List<Comment>> {
        return apiCallManager.loadGistComments(header, gist)
    }

}