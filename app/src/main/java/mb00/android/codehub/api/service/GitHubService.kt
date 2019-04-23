package mb00.android.codehub.api.service

import io.reactivex.Single
import mb00.android.codehub.api.model.*
import retrofit2.http.*

/**
 * Retrofit interface for the GitHub API
 */

interface GitHubService {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    fun getAccessToken(
            @Field("client_id") clientId: String,
            @Field("client_secret") clientSecret: String,
            @Field("code") code: String
    ): Single<AccessToken>

    @GET("search/repositories")
    fun repoSearch(@Header("Authorization") authHeader: String, @Query("q") repo: String): Single<RepoResult>

    @GET("search/users")
    fun userSearch(@Header("Authorization") authHeader: String, @Query("q") user: String): Single<UserResult>

    @GET("search/code")
    fun codeSearch(@Header("Authorization") authHeader: String, @Query("q") code: String): Single<CodeResult>

    @GET("search/issues")
    fun issueSearch(@Header("Authorization") authHeader: String, @Query("q") issue: String): Single<IssueResult>


    @GET("users/{user}")
    fun getUserOverview(@Header("Authorization") authHeader: String, @Path("user") user: String): Single<User>

    @GET("users/{user}/events")
    fun getUserPulse(@Header("Authorization") authHeader: String, @Path("user") user: String): Single<List<Pulse>>

    @GET("users/{user}/repos")
    fun getUserRepos(@Header("Authorization") authHeader: String, @Path("user") user: String): Single<List<Repo>>

    @GET("users/{user}/starred")
    fun getUserStarred(@Header("Authorization") authHeader: String, @Path("user") user: String): Single<List<Repo>>

    @GET("users/{user}/gists")
    fun getUserGists(@Header("Authorization") authHeader: String, @Path("user") user: String): Single<List<Gist>>

    @GET("users/{user}/followers")
    fun getUserFollowers(@Header("Authorization") authHeader: String, @Path("user") users: String): Single<List<User>>

    @GET("users/{user}/following")
    fun getUserFollowing(@Header("Authorization") authHeader: String, @Path("user") users: String): Single<List<User>>


    @GET("repos/{user}/{repo}/contents/{path}")
    fun getRepoContents(@Header("Authorization") authHeader: String, @Path("user") user: String, @Path("repo") repo: String, @Path("path") path: String): Single<List<Code>>

    @GET("repos/{user}/{repo}/readme")
    fun getRepoReadme(@Header("Authorization") authHeader: String, @Path("user") user: String, @Path("repo") repo: String): Single<Readme>

    @GET("repos/{user}/{repo}/contents/{filePath}")
    fun getRepoFile(@Header("Authorization") authHeader: String, @Path("user") user: String, @Path("repo") repo: String, @Path("filePath") filePath: String): Single<Code>

    @GET("repos/{user}/{repo}/license")
    fun getRepoLicense(@Header("Authorization") authHeader: String, @Path("user") user: String, @Path("repo") repo: String): Single<Code>

    @GET("repos/{user}/{repo}/issues")
    fun getRepoIssues(@Header("Authorization") authHeader: String, @Path("user") user: String, @Path("repo") repo: String): Single<List<Issue>>

    @GET("repos/{user}/{repo}/pulls")
    fun getRepoPullRequests(@Header("Authorization") authHeader: String, @Path("user") user: String, @Path("repo") repo: String): Single<List<PullRequest>>

    @GET("repos/{user}/{repo}/events")
    fun getRepoPulse(@Header("Authorization") authHeader: String, @Path("user") user: String, @Path("repo") repo: String): Single<List<Pulse>>

    @GET("repos/{user}/{repo}/commits")
    fun getRepoCommits(@Header("Authorization") authHeader: String, @Path("user") user: String, @Path("repo") repo: String): Single<List<Commit>>

    @GET("repos/{user}/{repo}/branches/{branch}")
    fun getRepoBranch(@Header("Authorization") authHeader: String, @Path("user") user: String, @Path("repo") repo: String, @Path("branch") branch: String): Single<List<Branch>>

    @GET("repos/{user}/{repo}/releases")
    fun getRepoReleases(@Header("Authorization") authHeader: String, @Path("user") user: String, @Path("repo") repo: String): Single<List<Release>>

    @GET("{releaseDownloadPath}")
    fun getRepoReleaseDownload(@Header("Authorization") authHeader: String, @Path("releaseDownloadPath") releaseDownloadPath: String): Single<Release>

    @GET("repos/{user}/{repo}/contributors")
    fun getRepoContributors(@Header("Authorization") authHeader: String, @Path("user") user: String, @Path("repo") repo: String): Single<List<Contributor>>


    @GET("gists/{gist}")
    fun getGistContents(@Header("Authorization") authHeader: String, @Path("gist") gist: String): Single<Gist>

    @GET("gists/{gist}/comments")
    fun getGistComments(@Header("Authorization") authHeader: String, @Path("gist") gist: String): Single<List<Comment>>

}
