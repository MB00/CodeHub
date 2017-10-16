package android.mb00.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Issue {

    @SerializedName("state")
    private String state;
    @SerializedName("number")
    private String number;
    @SerializedName("title")
    private String title;
    @SerializedName("created_at")
    private String openDate;
    @SerializedName("closed_at")
    private String closeDate;

    public String getState() {
        return state;
    }

    public String getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getOpenDate() {
        return openDate;
    }

    public String getCloseDate() {
        return closeDate;
    }
}
