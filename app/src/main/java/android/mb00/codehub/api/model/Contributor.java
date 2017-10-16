package android.mb00.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Contributor {

    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("contributions")
    private String contributions;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getContributions() {
        return contributions;
    }

}
