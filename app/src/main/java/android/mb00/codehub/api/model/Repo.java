package android.mb00.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Repo {

    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("description")
    private String description;
    @SerializedName("language")
    private String language;
    @SerializedName("stargazers_count")
    private String stargazersCount;
    @SerializedName("forks_count")
    private String forksCount;
    @SerializedName("owner")
    private Owner owner;

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getStargazersCount() {
        return stargazersCount;
    }

    public String getForksCount() {
        return forksCount;
    }

    public Owner getOwner() {
        return owner;
    }

}
