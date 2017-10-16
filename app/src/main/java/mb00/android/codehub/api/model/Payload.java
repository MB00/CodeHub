package mb00.android.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Payload {

    @SerializedName("issue")
    private Issue issue;

    public Issue getIssue() {
        return issue;
    }

}
