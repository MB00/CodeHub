package mb00.android.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Actor {

    // Mapping for nested JSON when getRepoPulse() is called in GitHubService.java

    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

}
