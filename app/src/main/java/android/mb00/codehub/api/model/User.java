package android.mb00.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("name")
    private String name;
    @SerializedName("company")
    private String company;
    @SerializedName("location")
    private String location;
    @SerializedName("email")
    private String email;
    @SerializedName("blog")
    private String website;
    @SerializedName("created_at")
    private String creationDate;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getCreationDate() {
        return creationDate;
    }

}
