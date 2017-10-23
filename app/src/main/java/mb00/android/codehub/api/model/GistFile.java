package mb00.android.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class GistFile {

    @SerializedName("filename")
    private String fileName;
    @SerializedName("size")
    private String size;
    @SerializedName("content")
    private String content;

    public String getFileName() {
        return fileName;
    }

    public String getSize() {
        return size;
    }

    public String getContent() {
        return content;
    }

}
