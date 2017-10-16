package android.mb00.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Readme {

    @SerializedName("name")
    private String name;
    @SerializedName("size")
    private String size;
    @SerializedName("content")
    private String content;

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getContent() {
        return content;
    }

}
