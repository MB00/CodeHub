package mb00.android.codehub.api.service;

import java.util.List;

import io.reactivex.Observable;
import mb00.android.codehub.api.model.Branch;
import mb00.android.codehub.api.model.Code;
import mb00.android.codehub.api.model.CodeResult;
import mb00.android.codehub.api.model.Comment;
import mb00.android.codehub.api.model.Commit;
import mb00.android.codehub.api.model.Contributor;
import mb00.android.codehub.api.model.Gist;
import mb00.android.codehub.api.model.Issue;
import mb00.android.codehub.api.model.IssueResult;
import mb00.android.codehub.api.model.PullRequest;
import mb00.android.codehub.api.model.Pulse;
import mb00.android.codehub.api.model.Readme;
import mb00.android.codehub.api.model.Release;
import mb00.android.codehub.api.model.Repo;
import mb00.android.codehub.api.model.RepoResult;
import mb00.android.codehub.api.model.User;
import mb00.android.codehub.api.model.UserResult;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit interface for the GitHub API
 */

public interface GitHubService {

    @GET("search/repositories")
    Observable<RepoResult> repoSearch(@Header("Authorization") String authHeader, @Query("q") String repo);

    @GET("search/users")
    Observable<UserResult> userSearch(@Header("Authorization") String authHeader, @Query("q") String user);

    @GET("search/code")
    Observable<CodeResult> codeSearch(@Header("Authorization") String authHeader, @Query("q") String code);

    @GET("search/issues")
    Observable<IssueResult> issueSearch(@Header("Authorization") String authHeader, @Query("q") String issue);


    @GET("users/{user}")
    Observable<User> getUserOverview(@Header("Authorization") String authHeader, @Path("user") String user);

    @GET("users/{user}/events")
    Observable<List<Pulse>> getUserPulse(@Header("Authorization") String authHeader, @Path("user") String user);

    @GET("users/{user}/repos")
    Observable<List<Repo>> getUserRepos(@Header("Authorization") String authHeader, @Path("user") String user);

    @GET("users/{user}/starred")
    Observable<List<Repo>> getUserStarred(@Header("Authorization") String authHeader, @Path("user") String user);

    @GET("users/{user}/gists")
    Observable<List<Gist>> getUserGists(@Header("Authorization") String authHeader, @Path("user") String user);

    @GET("users/{user}/followers")
    Observable<List<User>> getUserFollowers(@Header("Authorization") String authHeader, @Path("user") String users);

    @GET("users/{user}/following")
    Observable<List<User>> getUserFollowing(@Header("Authorization") String authHeader, @Path("user") String users);


    @GET("repos/{user}/{repo}/contents/{path}")
    Observable<List<Code>> getRepoContents(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo, @Path("path") String path);

    @GET("repos/{user}/{repo}/readme")
    Observable<Readme> getRepoReadme(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/contents/{filePath}")
    Observable<Code> getRepoFile(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo, @Path("filePath") String filePath);

    @GET("repos/{user}/{repo}/license")
    Observable<Code> getRepoLicense(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/issues")
    Observable<List<Issue>> getRepoIssues(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/pulls")
    Observable<List<PullRequest>> getRepoPullRequests(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/events")
    Observable<List<Pulse>> getRepoPulse(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/commits")
    Observable<List<Commit>> getRepoCommits(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/branches/{branch}")
    Observable<List<Branch>> getRepoBranch(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo, @Path("branch") String branch);

    @GET("repos/{user}/{repo}/releases")
    Observable<List<Release>> getRepoReleases(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("{releaseDownloadPath}")
    Observable<Release> getRepoReleaseDownload(@Header("Authorization") String authHeader, @Path("releaseDownloadPath") String releaseDownloadPath);

    @GET("repos/{user}/{repo}/contributors")
    Observable<List<Contributor>> getRepoContributors(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);


    @GET("gists/{gist}")
    Observable<Gist> getGistContents(@Header("Authorization") String authHeader, @Path("gist") String gist);

    @GET("gists/{gist}/comments")
    Observable<List<Comment>> getGistComments(@Header("Authorization") String authHeader, @Path("gist") String gist);

}
