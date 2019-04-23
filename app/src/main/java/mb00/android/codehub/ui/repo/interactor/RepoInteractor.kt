package mb00.android.codehub.ui.repo.interactor

import io.reactivex.Single
import mb00.android.codehub.api.model.*


interface RepoInteractor {
    fun loadRepoCode(header: String, user: String, repo: String, path: String): Single<List<Code>>
    fun loadRepoCommits(header: String, user: String, repo: String): Single<List<Commit>>
    fun loadRepoContributors(header: String, user: String, repo: String): Single<List<Contributor>>
    fun loadRepoFile(header: String, user: String, repo: String, filePath: String): Single<Code>
    fun loadRepoIssues(header: String, user: String, repo: String): Single<List<Issue>>
    fun loadRepoLicense(header: String, user: String, repo: String): Single<Code>
    fun loadRepoPullRequests(header: String, user: String, repo: String): Single<List<PullRequest>>
    fun loadRepoPulse(header: String, user: String, repo: String): Single<List<Pulse>>
    fun loadRepoReadme(header: String, user: String, repo: String): Single<Readme>
    fun loadRepoReleases(header: String, user: String, repo: String): Single<List<Release>>
}
