package mb00.android.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Code {

    @SerializedName("name")
    private String name;
    @SerializedName("path")
    private String path;
    @SerializedName("size")
    private String size;
    @SerializedName("type")
    private String type;
    @SerializedName("content")
    private String content;

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

}
