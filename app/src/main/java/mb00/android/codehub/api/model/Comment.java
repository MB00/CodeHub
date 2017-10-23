package mb00.android.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Comment {

    @SerializedName("user")
    private User user;
    @SerializedName("created_at")
    public String creationDate;
    @SerializedName("body")
    public String body;

    public User getUser() {
        return user;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getBody() {
        return body;
    }

}
