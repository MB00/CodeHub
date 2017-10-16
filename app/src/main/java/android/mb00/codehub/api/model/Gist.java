package android.mb00.codehub.api.model;

import com.google.gson.annotations.SerializedName;


public class Gist {

    @SerializedName("url")
    private String url;
    @SerializedName("files")
    private GistFile files;
    @SerializedName("description")
    private String description;
    @SerializedName("created_at")
    private String creationDate;
    @SerializedName("updated_at")
    private String updateDate;
    @SerializedName("comments")
    private int commentCount;

    public String getDescription() {
        return description;
    }

    public GistFile getFiles() {
        return files;
    }

    public String getUrl() {
        return url;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public int getCommentCount() {
        return commentCount;
    }

}
