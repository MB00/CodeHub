package android.mb00.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Payload {

    @SerializedName("issue")
    private Issue issue;

    public Issue getIssue() {
        return issue;
    }

}
