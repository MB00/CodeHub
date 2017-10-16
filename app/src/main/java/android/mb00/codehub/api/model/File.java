package android.mb00.codehub.api.model;


import com.google.gson.annotations.SerializedName;

public class File {

    @SerializedName("filename")
    private String fileName;
    @SerializedName("type")
    private String type;
    @SerializedName("language")
    private String language;
    @SerializedName("size")
    private String size;
    @SerializedName("content")
    private String content;

    public String getFileName() {
        return fileName;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public String getSize() {
        return size;
    }

    public String getContent() {
        return content;
    }

}
