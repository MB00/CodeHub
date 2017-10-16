package mb00.android.codehub.api.service;

import mb00.android.codehub.api.model.Branch;
import mb00.android.codehub.api.model.CodeResult;
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
import mb00.android.codehub.api.model.Code;
import mb00.android.codehub.api.model.RepoResult;
import mb00.android.codehub.api.model.User;
import mb00.android.codehub.api.model.UserResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface GitHubService {

    @GET("search/repositories")
    Call<RepoResult> repoSearch(@Header("Authorization") String authHeader, @Query("q") String repo);

    @GET("search/users")
    Call<UserResult> userSearch(@Header("Authorization") String authHeader, @Query("q") String user);

    @GET("search/code")
    Call<CodeResult> codeSearch(@Header("Authorization") String authHeader, @Query("q") String code);

    @GET("search/issues")
    Call<IssueResult> issueSearch(@Header("Authorization") String authHeader, @Query("q") String issue);


    @GET("users/{user}")
    Call<User> getUserOverview(@Header("Authorization") String authHeader, @Path("user") String user);

    @GET("users/{user}/events")
    Call<List<Pulse>> getUserPulse(@Header("Authorization") String authHeader, @Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<Repo>> getUserRepos(@Header("Authorization") String authHeader, @Path("user") String user);

    @GET("users/{user}/starred")
    Call<List<Repo>> getUserStarred(@Header("Authorization") String authHeader, @Path("user") String user);

    @GET("users/{user}/gists")
    Call<List<Gist>> getUserGists(@Header("Authorization") String authHeader, @Path("user") String user);

    @GET("users/{user}/followers")
    Call<List<User>> getUserFollowers(@Header("Authorization") String authHeader, @Path("user") String users);

    @GET("users/{user}/following")
    Call<List<User>> getUserFollowing(@Header("Authorization") String authHeader, @Path("user") String users);


    @GET("repos/{user}/{repo}/contents/{path}")
    Call<List<Code>> getRepoContents(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo, @Path("path") String path);

    @GET("repos/{user}/{repo}/readme")
    Call<Readme> getRepoReadme(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/contents/{filePath}")
    Call<Code> getRepoFile(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo, @Path("filePath") String filePath);

    @GET("repos/{user}/{repo}/license")
    Call<Code> getRepoLicense(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/issues")
    Call<List<Issue>> getRepoIssues(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/pulls")
    Call<List<PullRequest>> getRepoPullRequests(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/events")
    Call<List<Pulse>> getRepoPulse(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/commits")
    Call<List<Commit>> getRepoCommits(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("repos/{user}/{repo}/branches/{branch}")
    Call<List<Branch>> getRepoBranch(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo, @Path("branch") String branch);

    @GET("repos/{user}/{repo}/releases")
    Call<List<Release>> getRepoReleases(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

    @GET("{releaseDownloadPath}")
    Call<Release> getRepoReleaseDownload(@Header("Authorization") String authHeader, @Path("releaseDownloadPath") String releaseDownloadPath);

    @GET("repos/{user}/{repo}/contributors")
    Call<List<Contributor>> getRepoContributors(@Header("Authorization") String authHeader, @Path("user") String user, @Path("repo") String repo);

}
