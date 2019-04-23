package mb00.android.codehub.ui.gist.interactor

import io.reactivex.Single
import mb00.android.codehub.api.model.Comment
import mb00.android.codehub.api.model.Gist


interface GistInteractor {
    fun loadGistFiles(header: String, gist: String): Single<Gist>
    fun loadGistComments(header: String, gist: String): Single<List<Comment>>
}