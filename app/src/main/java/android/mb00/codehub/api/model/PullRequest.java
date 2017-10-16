package android.mb00.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class PullRequest {

    @SerializedName("state")
    private String state;
    @SerializedName("title")
    private String title;
    @SerializedName("number")
    private String number;
    @SerializedName("created_at")
    private String creationDate;
    @SerializedName("user")
    private User user;

    public String getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public String getNumber() {
        return number;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public User getUser() {
        return user;
    }

}
