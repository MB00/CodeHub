package mb00.android.codehub.ui.repo.viewmodel

import io.reactivex.Single
import mb00.android.codehub.api.model.Code
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter
import java.util.ArrayList


class RepoCodeViewModel(private val interactor: RepoInteractor, private val router: RepoRouter)
    : RepoViewModel(interactor, router) {

    fun loadRepoCode(header: String, user: String, repo: String, path: String): Single<List<Code>> {
        return interactor.loadRepoCode(header, user, repo, path)
    }

    fun sortCodeList(codeList: List<Code>): List<Code> {
        val sortedCodeList = ArrayList<Code>()

        for (code in codeList) {
            if (code.type == "dir") {
                sortedCodeList.add(code)
            }
        }
        for (code in codeList) {
            if (code.type == "file") {
                sortedCodeList.add(code)
            }
        }
        return sortedCodeList
    }

}
