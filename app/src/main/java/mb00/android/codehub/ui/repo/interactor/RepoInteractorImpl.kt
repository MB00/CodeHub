package mb00.android.codehub.ui.repo.interactor

import io.reactivex.Single
import mb00.android.codehub.api.manager.ApiCallManager
import mb00.android.codehub.api.model.*


class RepoInteractorImpl(private val apiCallManager: ApiCallManager) : RepoInteractor {

    override fun loadRepoCode(header: String, user: String, repo: String, path: String): Single<List<Code>> {
        return apiCallManager.loadRepoContents(header, user, repo, path)
    }

    override fun loadRepoCommits(header: String, user: String, repo: String): Single<List<Commit>> {
        return apiCallManager.loadRepoCommits(header, user, repo)
    }

    override fun loadRepoContributors(header: String, user: String, repo: String): Single<List<Contributor>> {
        return apiCallManager.loadRepoContributors(header, user, repo)
    }

    override fun loadRepoFile(header: String, user: String, repo: String, filePath: String): Single<Code> {
        return apiCallManager.loadRepoFile(header, user, repo, filePath)
    }

    override fun loadRepoIssues(header: String, user: String, repo: String): Single<List<Issue>> {
        return apiCallManager.loadRepoIssues(header, user, repo)
    }

    override fun loadRepoLicense(header: String, user: String, repo: String): Single<Code> {
        return apiCallManager.loadRepoLicense(header, user, repo)
    }

    override fun loadRepoPullRequests(header: String, user: String, repo: String): Single<List<PullRequest>> {
        return apiCallManager.loadRepoPullRequests(header, user, repo)
    }

    override fun loadRepoPulse(header: String, user: String, repo: String): Single<List<Pulse>> {
        return apiCallManager.loadRepoPulse(header, user, repo)
    }

    override fun loadRepoReadme(header: String, user: String, repo: String): Single<Readme> {
        return apiCallManager.loadRepoReadme(header, user, repo)
    }

    override fun loadRepoReleases(header: String, user: String, repo: String): Single<List<Release>> {
        return apiCallManager.loadRepoReleases(header, user, repo)
    }

}
