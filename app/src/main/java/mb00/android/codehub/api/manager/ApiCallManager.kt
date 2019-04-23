package mb00.android.codehub.api.manager

import android.content.Context
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mb00.android.codehub.api.builder.RetrofitBuilder
import mb00.android.codehub.api.model.*
import mb00.android.codehub.api.service.GitHubService
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.PreferenceKeys
import retrofit2.Retrofit


class ApiCallManager(context: Context) {

    private val authHeader: String?
    private val userName: String?
    private val repoName: String?

    private val retrofit: Retrofit
    private val service: GitHubService

    init {
        val preferences = context.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "")
        userName = preferences.getString(BundleKeys.USER_NAME, "")
        repoName = preferences.getString(BundleKeys.REPO_NAME, "")

        retrofit = RetrofitBuilder.instance
        service = retrofit.create(GitHubService::class.java)
    }

    fun loadAccess(clientId: String, clientSecret: String, code: String): Single<AccessToken> {
        return service.getAccessToken(clientId, clientSecret, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadRepoSearchResults(header: String, user: String): Single<RepoResult> {
        return service.repoSearch(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadUserSearchResults(header: String, user: String): Single<UserResult> {
        return service.userSearch(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadCodeSearchResults(header: String, user: String): Single<CodeResult> {
        return service.codeSearch(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadIssueSearchResults(header: String, user: String): Single<IssueResult> {
        return service.issueSearch(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadUserOverview(header: String, user: String): Single<User> {
        return service.getUserOverview(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadUserPulse(header: String, user: String): Single<List<Pulse>> {
        return service.getUserPulse(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadUserRepos(header: String, user: String): Single<List<Repo>> {
        return service.getUserRepos(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadUserStarred(header: String, user: String): Single<List<Repo>> {
        return service.getUserStarred(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadUserGists(header: String, user: String): Single<List<Gist>> {
        return service.getUserGists(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadUserFollowers(header: String, user: String): Single<List<User>> {
        return service.getUserFollowers(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadUserFollowing(header: String, user: String): Single<List<User>> {
        return service.getUserFollowing(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadRepoContents(header: String, user: String, repo: String, path: String): Single<List<Code>> {
        return service.getRepoContents(header, user, repo, path)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadRepoReadme(header: String, user: String, repo: String): Single<Readme> {
        return service.getRepoReadme(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadRepoFile(header: String, user: String, repo: String, filePath: String): Single<Code> {
        return service.getRepoFile(header, user, repo, filePath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadRepoLicense(header: String, user: String, repo: String): Single<Code> {
        return service.getRepoLicense(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadRepoIssues(header: String, user: String, repo: String): Single<List<Issue>> {
        return service.getRepoIssues(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadRepoPullRequests(header: String, user: String, repo: String): Single<List<PullRequest>> {
        return service.getRepoPullRequests(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadRepoPulse(header: String, user: String, repo: String): Single<List<Pulse>> {
        return service.getRepoPulse(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadRepoCommits(header: String, user: String, repo: String): Single<List<Commit>> {
        return service.getRepoCommits(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadRepoBranch(header: String, user: String, repo: String, branch: String): Single<List<Branch>> {
        return service.getRepoBranch(header, user, repo, branch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadRepoReleases(header: String, user: String, repo: String): Single<List<Release>> {
        return service.getRepoReleases(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadRepoContributors(header: String, user: String, repo: String): Single<List<Contributor>> {
        return service.getRepoContributors(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadGistContents(header: String, gist: String): Single<Gist> {
        return service.getGistContents(header, gist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadGistComments(header: String, gist: String): Single<List<Comment>> {
        return service.getGistComments(header, gist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
