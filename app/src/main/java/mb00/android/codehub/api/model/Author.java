package mb00.android.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Author {

    @SerializedName("name")
    private String name;
    @SerializedName("date")
    private String date;
    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;

    // Mapping for nested JSON when getRepoCommits() is called in GitHubService.java
    public String getName() {
        return name;
    }

    // Mapping for nested JSON when getRepoCommits() is called in GitHubService.java
    public String getDate() {
        return date;
    }

    // Mapping for nested JSON when getRepoReleases() is called in GitHubService.java
    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

}
