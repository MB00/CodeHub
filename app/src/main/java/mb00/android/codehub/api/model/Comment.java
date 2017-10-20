package mb00.android.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Comment {

    @SerializedName("created_at")
    public String creationDate;
    @SerializedName("body")
    public String body;

    public String getCreationDate() {
        return creationDate;
    }

    public String getBody() {
        return body;
    }

}
